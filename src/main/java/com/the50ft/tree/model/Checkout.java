package com.the50ft.tree.model;

/**
 * Created by bob on 6/30/17.
 */
public class Checkout {

    public Checkout(Person person, Aircraft aircraft) {
        this.person = person;
        this.aircraft = aircraft;
    }

    public Person getPerson() {
        return this.person;
    }

    public Aircraft getAircraft() {
        return this.aircraft;
    }

    private final Person person;

    private final Aircraft aircraft;
}
