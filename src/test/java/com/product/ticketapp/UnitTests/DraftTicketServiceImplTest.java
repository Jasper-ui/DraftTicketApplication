package com.product.ticketapp.UnitTests;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.DraftTicketResponse;
import com.product.ticketapp.entities.Luggage;
import com.product.ticketapp.entities.Passenger;
import com.product.ticketapp.services.DraftTicketServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class DraftTicketServiceImplTest {

  DraftTicketServiceImpl service = new DraftTicketServiceImpl();

  @Test
  public void acceptanceCriteriaTest() {
    List<Luggage> adultLuggage = List.of(new Luggage(), new Luggage());
    List<Luggage> childLuggage = List.of(new Luggage());

    Passenger adultPassenger = new Passenger(null, null, true, adultLuggage);
    Passenger childPassenger = new Passenger(null, null, false, childLuggage);

    List<Passenger> passengerList = List.of(adultPassenger, childPassenger);

    DraftTicketRequest draftTicket = new DraftTicketRequest(passengerList, null, null);

    DraftTicketResponse outputTickets = service.processDraftTicket(draftTicket);

    Assertions.assertEquals(
        outputTickets.getTotalTravelCost().stripTrailingZeros(), BigDecimal.valueOf(15));
    Assertions.assertEquals(
        outputTickets.getTotalLuggageCost().stripTrailingZeros(), BigDecimal.valueOf(9));
    Assertions.assertEquals(
        outputTickets.getTotalTicketPrice().stripTrailingZeros(), BigDecimal.valueOf(24));
    Assertions.assertEquals(
        outputTickets.getTotalTicketPriceWithTax().stripTrailingZeros(), BigDecimal.valueOf(29.04));
  }

  @Test
  public void manyPassengerTest() {
    List<Luggage> adultLuggage = List.of(new Luggage(), new Luggage());
    List<Luggage> childLuggage = List.of(new Luggage());
    List<Luggage> randLuggage = List.of(new Luggage(), new Luggage(), new Luggage(), new Luggage());

    Passenger adultPassenger = new Passenger("Dustin", "Desert", true, adultLuggage);
    Passenger childPassenger = new Passenger("Sherlock", "Holmes", false, childLuggage);
    Passenger randPassenger = new Passenger("Harry", "Soo", true, randLuggage);
    Passenger randPassenger2 = new Passenger("Elton", "John", true, new ArrayList<>());
    Passenger randPassenger3 = new Passenger("Someone", "here", true, randLuggage);

    List<Passenger> passengerList =
        List.of(adultPassenger, childPassenger, randPassenger, randPassenger2, randPassenger3);

    DraftTicketRequest draftTicket = new DraftTicketRequest(passengerList, null, null);

    DraftTicketResponse outputTickets = service.processDraftTicket(draftTicket);

    Assertions.assertEquals(
        outputTickets.getTotalTravelCost().stripTrailingZeros(), BigDecimal.valueOf(45));
    Assertions.assertEquals(
        outputTickets.getTotalLuggageCost().stripTrailingZeros(), BigDecimal.valueOf(33));
    Assertions.assertEquals(
        outputTickets.getTotalTicketPrice().stripTrailingZeros(), BigDecimal.valueOf(78));
    Assertions.assertEquals(
        outputTickets.getTotalTicketPriceWithTax().stripTrailingZeros(), BigDecimal.valueOf(94.38));
  }

  @Test
  public void nullDraftTicketTest() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.processDraftTicket(null));
  }

  @Test
  public void nullDraftTicketPassengerListTest() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> service.processDraftTicket(new DraftTicketRequest(null, null, null)));
  }

  @Test
  public void emptyDraftTicketPassengerListTest() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> service.processDraftTicket(new DraftTicketRequest(new ArrayList<>(), null, null)));
  }
}
