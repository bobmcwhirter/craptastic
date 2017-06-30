package com.the50ft.tree.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by bob on 6/19/17.
 */
public class TimeBlock {

    public TimeBlock(LocalTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
    }

    public LocalTime getStart() {
        return this.start;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public boolean overlaps(TimeBlock other) {
        LocalDate ref = LocalDate.now();

        return atDate(ref).overlaps(other.atDate(ref));
    }

    public boolean overlaps(DateTimeBlock other) {
        return atDate(other.getStart().toLocalDate()).overlaps(other);
    }

    public DateTimeBlock atDate(LocalDate date) {
        return new DateTimeBlock(this.start.atDate(date), this.duration);
    }

    public boolean contains(DateTimeBlock other) {
        return atDate(other.getStart().toLocalDate()).contains(other);
    }

    private final LocalTime start;

    private final Duration duration;

}
