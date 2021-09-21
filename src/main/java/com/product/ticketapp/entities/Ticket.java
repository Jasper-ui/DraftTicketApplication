package com.product.ticketapp.entities;

import java.math.BigDecimal;

public class Ticket {
  String firstName;
  String lastName;
  BigDecimal individualTravelCost;
  BigDecimal individualLuggageCost;
  BigDecimal individualTotalCost;
  BigDecimal individualTotalWithTax;

  public Ticket(
      String firstName,
      String lastName,
      BigDecimal individualTravelCost,
      BigDecimal individualLuggageCost,
      BigDecimal individualTotalCost,
      BigDecimal individualTotalWithTax) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.individualTravelCost = individualTravelCost;
    this.individualLuggageCost = individualLuggageCost;
    this.individualTotalCost = individualTotalCost;
    this.individualTotalWithTax = individualTotalWithTax;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public BigDecimal getIndividualTravelCost() {
    return individualTravelCost;
  }

  public void setIndividualTravelCost(BigDecimal individualTravelCost) {
    this.individualTravelCost = individualTravelCost;
  }

  public BigDecimal getIndividualLuggageCost() {
    return individualLuggageCost;
  }

  public void setIndividualLuggageCost(BigDecimal individualLuggageCost) {
    this.individualLuggageCost = individualLuggageCost;
  }

  public BigDecimal getIndividualTotalCost() {
    return individualTotalCost;
  }

  public void setIndividualTotalCost(BigDecimal individualTotalCost) {
    this.individualTotalCost = individualTotalCost;
  }

  public BigDecimal getIndividualTotalWithTax() {
    return individualTotalWithTax;
  }

  public void setIndividualTotalWithTax(BigDecimal individualTotalWithTax) {
    this.individualTotalWithTax = individualTotalWithTax;
  }
}
