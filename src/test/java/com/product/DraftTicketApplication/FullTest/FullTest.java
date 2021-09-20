package com.product.DraftTicketApplication.FullTest;

import com.product.DraftTicketApplication.entities.*;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void acceptanceCriteriaTest() {

        List<Luggage> adultLuggage = List.of(new Luggage(), new Luggage());
        List<Luggage> childLuggage = List.of(new Luggage());

        Passenger adultPassenger = new Passenger("Gordon", "Ramsey", true, adultLuggage);
        Passenger childPassenger = new Passenger("Macaulay ", "Culkin", false, childLuggage);

        List<Passenger> passengerList = List.of(adultPassenger, childPassenger);

        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(passengerList,"Vilnius", "Riga");

        ResponseEntityTickets result = submitPostRequest(draftTicket, ResponseEntityTickets.class);

        assert result != null;
        Assertions.assertEquals(result.getTotalTravelCost().stripTrailingZeros(), BigDecimal.valueOf(15));
        Assertions.assertEquals(result.getTotalLuggageCost().stripTrailingZeros(), BigDecimal.valueOf(9));
        Assertions.assertEquals(result.getTotalTicketPrice().stripTrailingZeros(), BigDecimal.valueOf(24));
        Assertions.assertEquals(result.getTotalTicketPriceWithTax().stripTrailingZeros(), BigDecimal.valueOf(29.04));
    }

    @Test
    public void passengersListNullRequest() {
        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(null, "Vilnius", "Riga");

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "passengersList","Passengers list cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    @Test
    public void passengersListEmptyRequest() {
        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(new ArrayList<>(), "Vilnius", "Riga");

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "passengersList","Passengers list cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    @Test
    public void departureNullRequest() {
        Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(List.of(passenger), null, "Riga");

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "departure","Departure location cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    @Test
    public void departureEmptyRequest() {
        Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(List.of(passenger), "", "Riga");

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "departure","Departure location cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    @Test
    public void arrivalNullRequest() {
        Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(List.of(passenger), "Hell", null);

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "arrival","Arrival location cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    @Test
    public void arrivalEmptyRequest() {
        Passenger passenger = new Passenger("Alucard", "Dracula", true, new ArrayList<>());

        RequestEntityDraftTicket draftTicket = new RequestEntityDraftTicket(List.of(passenger), "Hell", "");

        ValidationErrorResponse expectedResponse = new ValidationErrorResponse(400, "arrival","Arrival location cannot be null or empty" );

        ValidationErrorResponse result = submitPostRequest(draftTicket, ValidationErrorResponse.class);

        assertValidationErrors(expectedResponse, result);
    }

    public <T> T submitPostRequest(RequestEntityDraftTicket draftTicket, Class<T> className){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RequestEntityDraftTicket> request = new HttpEntity<>(draftTicket, headers);

        return this.restTemplate.postForEntity("http://localhost:" + port + "/", request, className).getBody();
    }

    public void assertValidationErrors(ValidationErrorResponse expected, ValidationErrorResponse received)
    {
        assert received != null;
        Assertions.assertEquals(expected.getResponseCode(), received.getResponseCode());
        Assertions.assertEquals(expected.getFieldName(), received.getFieldName());
        Assertions.assertEquals(expected.getErrorMessage(), received.getErrorMessage());
    }
}
