package com.product.DraftTicketApplication.entities;

import java.util.List;

public class Passenger {
    String firstName;
    String lastName;
    boolean adult;
    List<Luggage> luggageList;

    public Passenger(String firstName, String lastName, boolean adult, List<Luggage> luggageList){
        this.firstName = firstName;
        this.lastName = lastName;
        this.adult = adult;
        this.luggageList = luggageList;
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

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<Luggage> getLuggageList() {
        return luggageList;
    }

    public void setAdult(List<Luggage> luggageList) {
        this.luggageList = luggageList;
    }

}
