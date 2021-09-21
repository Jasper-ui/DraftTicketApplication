package com.product.ticketapp.services;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.DraftTicketResponse;
import com.product.ticketapp.entities.Passenger;
import com.product.ticketapp.entities.Ticket;
import com.product.ticketapp.interfaces.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DraftTicketServiceImpl implements TicketService {

  @Override
  public DraftTicketResponse processDraftTicket(DraftTicketRequest draftTicket) {
    Assert.notNull(draftTicket, "Submitted draft ticket is null");
    Assert.notNull(draftTicket.getPassengerList(), "Submitted draft ticket passenger list is null");
    Assert.notEmpty(
        draftTicket.getPassengerList(), "Submitted draft ticket passenger list is empty");

    BigDecimal travelRate = getTravelRate(draftTicket.getArrival(), draftTicket.getDeparture());
    BigDecimal taxRate = getTaxRate();

    List<Ticket> individualTickets = new ArrayList<>();

    for (Passenger passenger : draftTicket.getPassengerList()) {
      individualTickets.add(createIndividualTicket(passenger, travelRate, taxRate));
    }

    return createResponseTicket(draftTicket, individualTickets);
  }

  private BigDecimal getTravelRate(String arrival, String departure) {
    // Retrieval from external service and validation
    return new BigDecimal(10);
  }

  private BigDecimal getTaxRate() {
    // Retrieval from external service and validation
    return new BigDecimal(21);
  }

  private Ticket createIndividualTicket(
      Passenger passenger, BigDecimal travelRate, BigDecimal taxRate) {
    String firstName = passenger.getFirstName();
    String lastName = passenger.getLastName();

    BigDecimal individualTravelCost =
        passenger.getAdult() ? travelRate : travelRate.multiply(BigDecimal.valueOf(0.5));

    BigDecimal luggageCost =
        travelRate
            .multiply(BigDecimal.valueOf(0.3))
            .multiply(BigDecimal.valueOf(passenger.getLuggageList().size()));
    BigDecimal totalCost = individualTravelCost.add(luggageCost);

    BigDecimal totalCostWithTax =
        taxRate.multiply(BigDecimal.valueOf(0.01)).multiply(totalCost).add(totalCost);

    return new Ticket(
        firstName, lastName, individualTravelCost, luggageCost, totalCost, totalCostWithTax);
  }

  private DraftTicketResponse createResponseTicket(
      DraftTicketRequest draftTicket, List<Ticket> individualTickets) {
    DraftTicketResponse tickets = new DraftTicketResponse();

    tickets.setDeparture(draftTicket.getDeparture());
    tickets.setArrival(draftTicket.getArrival());
    tickets.setIndividualTickets(individualTickets);
    tickets.setTotalTravelCost(tickets.calculateTotalTravelCost());
    tickets.setTotalLuggageCost(tickets.calculateTotalLuggageCost());
    tickets.setTotalTicketPrice(tickets.calculateTotalTicketCost());
    tickets.setTotalTicketPriceWithTax(tickets.calculateTotalTicketCostWithTax());

    return tickets;
  }
}
