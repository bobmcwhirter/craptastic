package com.the50ft.tree.schedule.memory;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.schedule.MutableSchedule;
import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
public class InMemorySchedule<T extends Resource> implements MutableSchedule<T> {

    public InMemorySchedule(T resource) {
        this.resource = resource;
    }

    @Override
    public T getResource() {
        return this.resource;
    }

    @Override
    public void available(LocalTime start, LocalTime end, DayOfWeek... days) {
        this.rules.addAll(Availability.available(start, end, days));
    }

    @Override
    public void unavailable(LocalTime start, LocalTime end, DayOfWeek... days) {
        this.rules.addAll(Availability.unavailable(start, end, days));
    }

    @Override
    public void available(DateTimeBlock block) {
        this.availabilities.add(Availability.available(block));
    }

    @Override
    public void unavailable(DateTimeBlock block) {
        this.availabilities.add(Availability.unavailable(block));
    }

    @Override
    public boolean isAvailable(DateTimeBlock block) {
        return isGenerallyAvailable(block)
                .refine(isSpecificallyAvailable(block)) == Availability.Status.AVAILABLE;
    }

    @Override
    public Duration availabilityBefore(DateTimeBlock block) {
        LocalDateTime end = block.getStart();
        LocalDateTime start = block.getStart();

        LocalDateTime sameDay = start.truncatedTo(ChronoUnit.DAYS);

        Duration availability = Duration.ZERO;

        while (start.isAfter(sameDay)) {
            Duration candidate = Duration.between(start, end);
            if (!isAvailable(new DateTimeBlock(start, candidate))) {
                return availability;
            }
            availability = candidate;
            start = start.minus(15, ChronoUnit.MINUTES);
        }

        return availability;
    }

    @Override
    public Duration availabilityAfter(DateTimeBlock block) {
        LocalDateTime start = block.getEnd();
        LocalDateTime end = block.getEnd();

        LocalDateTime nextDay = start.truncatedTo(ChronoUnit.DAYS).plus(1, ChronoUnit.DAYS);
        Duration availability = Duration.ZERO;

        while (end.isBefore(nextDay)) {
            Duration candidate = Duration.between(start, end);
            if (!isAvailable(new DateTimeBlock(start, candidate))) {
                return availability;
            }
            availability = candidate;
            end = end.plus(15, ChronoUnit.MINUTES);
        }

        return availability;
    }

    protected Availability.Status isGenerallyAvailable(DateTimeBlock block) {
        return isAvailable(this.rules, block);
    }

    protected Availability.Status isSpecificallyAvailable(DateTimeBlock block) {
        return isAvailable(this.availabilities, block);
    }

    private Availability.Status isAvailable(List<? extends Availability> items, DateTimeBlock block) {
        for (Availability item : items) {
            Availability.Status result = item.test(block);
            if (result != Availability.Status.UNKNOWN) {
                return result;
            }
        }

        return Availability.Status.UNKNOWN;
    }

    private final T resource;

    private List<Rule> rules = new ArrayList<>();

    private List<Availability> availabilities = new ArrayList<>();

}
