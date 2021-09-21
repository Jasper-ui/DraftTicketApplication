package com.product.ticketapp.controllers.requests;

import com.product.ticketapp.entities.Passenger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DraftTicketRequest {
  @NotEmpty(message = "Passengers list cannot be null or empty")
  private List<Passenger> passengersList;

  @NotNull(message = "Arrival location cannot be null or empty")
  @Size(min = 3, max = 12, message = "Arrival location size needs to be between 3-12 characters")
  private String arrival;

  @NotNull(message = "Departure location cannot be null or empty")
  @Size(min = 3, max = 12, message = "Departure location size needs to be between 3-12 characters")
  private String departure;

  public DraftTicketRequest(List<Passenger> passengersList, String departure, String arrival) {
    this.setPassengersList(passengersList);
    this.setDeparture(departure);
    this.setArrival(arrival);
  }

  public List<Passenger> getPassengerList() {
    return getPassengersList();
  }

  public void setPassengerList(List<Passenger> passengersList) {
    this.setPassengersList(passengersList);
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

  public List<Passenger> getPassengersList() {
    return passengersList;
  }

  public void setPassengersList(List<Passenger> passengersList) {
    this.passengersList = passengersList;
  }
}
