package com.product.DraftTicketApplication.services;

import com.product.DraftTicketApplication.entities.RequestEntityDraftTicket;
import com.product.DraftTicketApplication.entities.Passenger;
import com.product.DraftTicketApplication.entities.RequestEntityDraftTicket;
import com.product.DraftTicketApplication.entities.ResponseEntityTickets;
import com.product.DraftTicketApplication.entities.Ticket;
import com.product.DraftTicketApplication.interfaces.TicketInterface;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DraftTicketService implements TicketInterface {

    @Override
    public ResponseEntityTickets processDraftTicket(RequestEntityDraftTicket draftTicket) {
        Assert.notNull(draftTicket, "Submitted draft ticket is null");
        Assert.notNull(draftTicket.getPassengerList(), "Submitted draft ticket passenger list is null");
        Assert.notEmpty(draftTicket.getPassengerList(), "Submitted draft ticket passenger list is empty");

        BigDecimal travelRate = getTravelRate(draftTicket.getArrival(), draftTicket.getDeparture());
        BigDecimal taxRate = getTaxRate();

        List<Ticket> individualTickets = new ArrayList<>();

        for (Passenger passenger : draftTicket.getPassengerList()) {
            createIndividualTickets(passenger, individualTickets, travelRate, taxRate);
        }

        return createResponseTicket(individualTickets);
    }

    private BigDecimal getTravelRate(String arrival, String departure) {
        //Retrieval from DB and validation
        return new BigDecimal(10).setScale(5, RoundingMode.HALF_UP);
    }

    private BigDecimal getTaxRate() {
        //Retrieval from DB and validation
        return new BigDecimal(21).setScale(5, RoundingMode.HALF_UP);
    }

    private void createIndividualTickets(Passenger passenger , List<Ticket> individualTickets, BigDecimal travelRate, BigDecimal taxRate) {
        Ticket newTicket = new Ticket();

        newTicket.setIndividualTravelCost(
                passenger.getAdult() ? travelRate : travelRate.multiply(BigDecimal.valueOf(0.5))
        );

        newTicket.setIndividualLuggageCost(travelRate.multiply(BigDecimal.valueOf(0.3)).multiply(BigDecimal.valueOf(passenger.getLuggageList().size())));
        newTicket.setIndividualTotalCost(newTicket.getIndividualTravelCost().add(newTicket.getIndividualLuggageCost()));
        newTicket.setIndividualTotalWithTax(taxRate.multiply(BigDecimal.valueOf(0.01)).multiply(newTicket.getIndividualTotalCost()).add(newTicket.getIndividualTotalCost()));
        individualTickets.add(newTicket);
    }

    private ResponseEntityTickets createResponseTicket(List<Ticket> individualTickets) {
        ResponseEntityTickets tickets = new ResponseEntityTickets();

        tickets.setIndividualTickets(individualTickets);
        tickets.setTotalTravelCost(tickets.calculateTotalTravelCost());
        tickets.setTotalLuggageCost(tickets.calculateTotalLuggageCost());
        tickets.setTotalTicketPrice(tickets.calculateTotalTicketCost());
        tickets.setTotalTicketPriceWithTax(tickets.calculateTotalTicketCostWithTax());

        return tickets;
    }
}
