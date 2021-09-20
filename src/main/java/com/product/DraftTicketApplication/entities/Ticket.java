package com.product.DraftTicketApplication.entities;

import java.math.BigDecimal;

public class Ticket {
    BigDecimal individualTravelCost;
    BigDecimal individualLuggageCost;
    BigDecimal individualTotalCost;
    BigDecimal individualTotalWithTax;

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
