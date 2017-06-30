package com.the50ft.tree.rules;

import java.util.List;
import java.util.stream.Collectors;

import com.the50ft.tree.model.Person;
import com.the50ft.tree.resources.ResourceCatalog;
import com.the50ft.tree.schedule.Schedules;
import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

/**
 * Created by bob on 6/30/17.
 */
public class DualInstruction implements RuleUnit {

    public DualInstruction(Schedules schedules, ResourceCatalog catalog) {
        //this.schedules = schedules.all().collect(Collectors.toList());
        this.persons = catalog.of(Person.class).all().collect(Collectors.toList());
        //this.aircrafts = catalog.of(Aircraft.class).all().collect(Collectors.toList());
    }

    //public DataSource<Schedule> getSchedules() {
        //return DataSource.create( this.sch)
    //}

    public DataSource<Person> getPersons() {
        System.err.println( "get persons: " + this.persons );
        return DataSource.create(this.persons.toArray( new Person[] {} ));
    }

    //public List<Aircraft> getAircrafts() {
        //return this.aircrafts;
    //}

    @Override
    public void onStart() {
        System.out.println("started.");
    }

    @Override
    public void onEnd() {
        System.out.println("ended.");
    }

    private final List<Person> persons;
}
