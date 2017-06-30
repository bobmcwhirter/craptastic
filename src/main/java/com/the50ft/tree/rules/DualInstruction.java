package com.the50ft.tree.rules;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.tools.javac.comp.Check;
import com.the50ft.tree.model.Aircraft;
import com.the50ft.tree.model.Checkout;
import com.the50ft.tree.model.Person;
import com.the50ft.tree.resources.ResourceCatalog;
import com.the50ft.tree.schedule.Schedules;
import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

/**
 * Created by bob on 6/30/17.
 */
public class DualInstruction implements RuleUnit {

    public DualInstruction(Person requestor, Schedules schedules, ResourceCatalog catalog, Collection<Checkout> checkouts) {
        this.requestor = DataSource.create(requestor);
        this.persons = DataSource.create( catalog.of(Person.class).all().collect(Collectors.toList()).toArray( new Person[]{} ) );
        this.aircrafts = DataSource.create( catalog.of(Aircraft.class).all().collect(Collectors.toList()).toArray(new Aircraft[]{} ) );
        this.checkouts = DataSource.create( checkouts.toArray( new Checkout[] {} ));
    }

    public DataSource<Person> getRequestor() {
        return this.requestor;
    }

    public DataSource<Person> getPersons() {
        System.err.println( "get persons: " + this.persons );
        return this.persons;
    }

    public DataSource<Aircraft> getAircrafts() {
        return this.aircrafts;
    }

    public DataSource<Checkout> getCheckouts() {
        return this.checkouts;
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
    private final DataSource<Person> requestor;
}
