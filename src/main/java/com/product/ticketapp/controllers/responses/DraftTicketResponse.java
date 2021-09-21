package com.product.ticketapp.controllers.responses;

import com.product.ticketapp.entities.Ticket;

import java.math.BigDecimal;
import java.util.List;

public class DraftTicketResponse {
  List<Ticket> individualTickets;
  String departure;
  String arrival;
  BigDecimal totalTravelCost;
  BigDecimal totalLuggageCost;
  BigDecimal totalTicketPrice;
  BigDecimal totalTicketPriceWithTax;

  public List<Ticket> getIndividualTickets() {
    return individualTickets;
  }

  public void setIndividualTickets(List<Ticket> individualTickets) {
    this.individualTickets = individualTickets;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    this.arrival = arrival;
  }

  public BigDecimal getTotalTravelCost() {
    return totalTravelCost;
  }

  public void setTotalTravelCost(BigDecimal totalTravelCost) {
    this.totalTravelCost = totalTravelCost;
  }

  public BigDecimal getTotalLuggageCost() {
    return totalLuggageCost;
  }

  public void setTotalLuggageCost(BigDecimal totalLuggageCost) {
    this.totalLuggageCost = totalLuggageCost;
  }

  public BigDecimal getTotalTicketPrice() {
    return totalTicketPrice;
  }

  public void setTotalTicketPrice(BigDecimal totalTicketPrice) {
    this.totalTicketPrice = totalTicketPrice;
  }

  public BigDecimal getTotalTicketPriceWithTax() {
    return totalTicketPriceWithTax;
  }

  public void setTotalTicketPriceWithTax(BigDecimal totalTicketPriceWithTax) {
    this.totalTicketPriceWithTax = totalTicketPriceWithTax;
  }

  public BigDecimal calculateTotalTravelCost() {
    BigDecimal totalCost = BigDecimal.ZERO;

    for (Ticket ticket : individualTickets) {
      totalCost = totalCost.add(ticket.getIndividualTravelCost());
    }

    return totalCost;
  }

  public BigDecimal calculateTotalTicketCost() {
    BigDecimal totalCost = BigDecimal.ZERO;

    for (Ticket ticket : individualTickets) {
      totalCost = totalCost.add(ticket.getIndividualTotalCost());
    }

    return totalCost;
  }

  public BigDecimal calculateTotalLuggageCost() {
    BigDecimal totalCost = BigDecimal.ZERO;

    for (Ticket ticket : individualTickets) {
      totalCost = totalCost.add(ticket.getIndividualLuggageCost());
    }

    return totalCost;
  }

  public BigDecimal calculateTotalTicketCostWithTax() {
    BigDecimal totalCost = BigDecimal.ZERO;

    for (Ticket ticket : individualTickets) {
      totalCost = totalCost.add(ticket.getIndividualTotalWithTax());
    }

    return totalCost;
  }
}
