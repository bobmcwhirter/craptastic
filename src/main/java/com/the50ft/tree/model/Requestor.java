package com.the50ft.tree.model;

/**
 * Created by bob on 6/30/17.
 */
public class Requestor {

    public Requestor(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }

    private final Person person;
}
