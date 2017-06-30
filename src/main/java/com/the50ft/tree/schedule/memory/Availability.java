package com.the50ft.tree.schedule.memory;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.the50ft.tree.time.DateTimeBlock;
import com.the50ft.tree.time.DayTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
interface Availability {
    enum Status {
        AVAILABLE,
        UNAVAILABLE,
        UNKNOWN;

        public Status refine(Status other) {
            if ( this == UNKNOWN ) {
                return other;
            }
            if ( other == UNKNOWN ) {
                return this;
            }
            return other;
        }
    }

    Status test(DateTimeBlock block);

    static Availability available(DateTimeBlock block) {
        return new DateTimeAvailability(block);
    }

    static Availability unavailable(DateTimeBlock block) {
        return new DateTimeUnavailability(block);
    }

    static Collection<Rule> available(LocalTime start, LocalTime end, DayOfWeek... days) {
        List<Rule> rules = new ArrayList<>();

        for (DayOfWeek day : days) {
            rules.add(new Rule(Status.AVAILABLE, new DayTimeBlock(day, start, Duration.between(start, end))));
        }

        return rules;
    }

    static Collection<Rule> unavailable(LocalTime start, LocalTime end, DayOfWeek... days) {
        List<Rule> rules = new ArrayList<>();

        for (DayOfWeek day : days) {
            rules.add(new Rule(Status.UNAVAILABLE, new DayTimeBlock(day, start, Duration.between(start, end))));
        }

        return rules;
    }
}
