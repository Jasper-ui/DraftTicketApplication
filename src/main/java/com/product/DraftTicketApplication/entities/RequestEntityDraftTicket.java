package com.product.DraftTicketApplication.entities;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RequestEntityDraftTicket {
    @NotEmpty(message = "Passengers list cannot be null or empty")
    private List<Passenger> passengersList;
    @NotEmpty(message = "Arrival location cannot be null or empty")
    private String arrival;
    @NotEmpty(message = "Departure location cannot be null or empty")
    private String departure;

    public RequestEntityDraftTicket(List<Passenger> passengersList, String departure, String arrival) {
        this.passengersList = passengersList;
        this.departure = departure;
        this.arrival = arrival;
    }

    public List<Passenger> getPassengerList() {
        return passengersList;
    }

    public void setPassengerList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

}
