package com.the50ft.tree.model;

/**
 * Created by bob on 6/30/17.
 */
public class Request {

    public Request(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }

    private final Person person;
}
