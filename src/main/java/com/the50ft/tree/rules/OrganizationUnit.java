package com.the50ft.tree.rules;

import java.util.Collection;
import java.util.stream.Collectors;

import com.the50ft.tree.model.Aircraft;
import com.the50ft.tree.model.Checkout;
import com.the50ft.tree.model.Person;
import com.the50ft.tree.model.Request;
import com.the50ft.tree.resources.ResourceCatalog;
import com.the50ft.tree.schedule.Schedules;
import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

/**
 * Created by bob on 6/30/17.
 */
public class OrganizationUnit implements RuleUnit {

    public OrganizationUnit() {
        this.persons = DataSource.create();
        this.aircrafts = DataSource.create();
        this.checkouts = DataSource.create();
        this.requests = DataSource.create();
    }

    public void insertPerson(Person person) {
        this.persons.insert( person);
    }

    public DataSource<Person> getPersons() {
        return this.persons;
    }

    public void insertAircraft(Aircraft aircraft) {
        this.aircrafts.insert(aircraft);
    }

    public DataSource<Aircraft> getAircrafts() {
        return this.aircrafts;
    }

    public void insertCheckout(Checkout checkout) {
        this.checkouts.insert(checkout);
    }

    public DataSource<Checkout> getCheckouts() {
        return this.checkouts;
    }

    public void insertRequest(Request request) {
        this.requests.insert(request);
    }

    public DataSource<Request> getRequests() {
        return this.requests;
    }

    @Override
    public void onStart() {
        System.out.println("started.");
    }

    @Override
    public void onEnd() {
        System.out.println("ended.");
    }

    private final DataSource<Person> persons;
    private final DataSource<Aircraft> aircrafts;
    private final DataSource<Checkout> checkouts;
    private final DataSource<Request> requests;
}
