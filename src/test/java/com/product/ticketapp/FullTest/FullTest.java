package com.product.ticketapp.FullTest;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.DraftTicketResponse;
import com.product.ticketapp.entities.Luggage;
import com.product.ticketapp.entities.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void acceptanceCriteriaTest() {

    List<Luggage> adultLuggage = List.of(new Luggage(), new Luggage());
    List<Luggage> childLuggage = List.of(new Luggage());

    Passenger adultPassenger = new Passenger("Gordon", "Ramsey", true, adultLuggage);
    Passenger childPassenger = new Passenger("Macaulay ", "Culkin", false, childLuggage);

    List<Passenger> passengerList = List.of(adultPassenger, childPassenger);

    DraftTicketRequest draftTicket = new DraftTicketRequest(passengerList, "Vilnius", "Riga");

    DraftTicketResponse result = submitPostRequest(draftTicket, DraftTicketResponse.class);

    assert result != null;
    Assertions.assertEquals(
        result.getTotalTravelCost().stripTrailingZeros(), BigDecimal.valueOf(15));
    Assertions.assertEquals(
        result.getTotalLuggageCost().stripTrailingZeros(), BigDecimal.valueOf(9));
    Assertions.assertEquals(
        result.getTotalTicketPrice().stripTrailingZeros(), BigDecimal.valueOf(24));
    Assertions.assertEquals(
        result.getTotalTicketPriceWithTax().stripTrailingZeros(), BigDecimal.valueOf(29.04));
  }

  public <T> T submitPostRequest(DraftTicketRequest draftTicket, Class<T> className) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<DraftTicketRequest> request = new HttpEntity<>(draftTicket, headers);

    return this.restTemplate
        .postForEntity("http://localhost:" + port + "/", request, className)
        .getBody();
  }
}
