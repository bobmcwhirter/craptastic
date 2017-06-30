package com.the50ft.tree.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by bob on 6/19/17.
 */
public class DayTimeBlock extends TimeBlock {

    public DayTimeBlock(DayOfWeek day, LocalTime start, Duration duration) {
        super(start, duration);
        this.day = day;
    }

    public DayOfWeek getDay() {
        return this.day;
    }

    public boolean overlaps(DateTimeBlock other) {
        if ( other.getStart().getDayOfWeek() != this.day ) {
            return false;

        }
        return super.overlaps(other.toTimeBlock());
    }

    private final DayOfWeek day;
}
