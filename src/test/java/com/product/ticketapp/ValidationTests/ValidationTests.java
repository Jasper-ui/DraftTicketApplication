package com.product.ticketapp.ValidationTests;

import com.product.ticketapp.controllers.requests.DraftTicketRequest;
import com.product.ticketapp.controllers.responses.ValidationErrorResponse;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidationTests {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void passengersListNullRequest() {
    DraftTicketRequest draftTicket = new DraftTicketRequest(null, "Vilnius", "Riga");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "passengersList", "Passengers list cannot be null or empty");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void passengersListEmptyRequest() {
    DraftTicketRequest draftTicket = new DraftTicketRequest(new ArrayList<>(), "Vilnius", "Riga");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "passengersList", "Passengers list cannot be null or empty");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void departureNullRequest() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), null, "Riga");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(400, "departure", "Departure location cannot be null or empty");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void departureEmptyRequest() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), "", "Riga");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "departure", "Departure location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void departureTooLong() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket =
        new DraftTicketRequest(List.of(passenger), "aaaabbbbccccdddd", "Hell");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "departure", "Departure location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void departureTooShort() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), "sa", "Heaven");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "departure", "Departure location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void arrivalNullRequest() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), "Hell", null);

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(400, "arrival", "Arrival location cannot be null or empty");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void arrivalEmptyRequest() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), "Hell", "");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "arrival", "Arrival location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void arrivalTooLong() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket =
        new DraftTicketRequest(List.of(passenger), "Hell", "aaaabbbbccccdddd");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "arrival", "Arrival location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  @Test
  public void arrivalTooShort() {
    Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

    DraftTicketRequest draftTicket = new DraftTicketRequest(List.of(passenger), "Hell", "sa");

    ValidationErrorResponse expectedResponse =
        new ValidationErrorResponse(
            400, "arrival", "Arrival location size needs to be between 3-12 characters");

    ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

    assertValidationErrors(expectedResponse, result);
  }

  public <T> T submitPostRequest(DraftTicketRequest draftTicket, Class<T> className) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<DraftTicketRequest> request = new HttpEntity<>(draftTicket, headers);

    return this.restTemplate
        .postForEntity("http://localhost:" + port + "/", request, className)
        .getBody();
  }

  public void assertValidationErrors(
      ValidationErrorResponse expected, ValidationErrorResponse received) {
    assert received != null;
    Assertions.assertEquals(expected.getResponseCode(), received.getResponseCode());
    Assertions.assertEquals(expected.getFieldName(), received.getFieldName());
    Assertions.assertEquals(expected.getErrorMessage(), received.getErrorMessage());
  }
}
