package com.the50ft.tree.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by bob on 6/19/17.
 */
public class DateTimeBlock {

    public DateTimeBlock(LocalDateTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public TimeBlock toTimeBlock() {
        return new TimeBlock(this.start.toLocalTime(), this.duration);
    }

    public boolean overlaps(DateTimeBlock other) {

        if (getStart().compareTo(other.getStart()) == 0) {
            return true;
        }

        if (getStart().isBefore(other.getStart())) {
            if (getEnd().compareTo(other.getStart()) <= 0) {
                return false;
            }
            return true;
        }

        if (other.getStart().isBefore(getStart())) {
            if (other.getEnd().compareTo(getStart()) <= 0) {
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean contains(DateTimeBlock other) {
        if (other.getStart().compareTo(getStart()) >= 0){
            if (other.getEnd().compareTo(getEnd()) <= 0) {
                return true;
            }
        }

        return false;
    }


    public LocalDateTime getEnd() {
        return this.start.plus(this.duration);
    }

    public DateTimeBlock move(int quantity, ChronoUnit units) {
        return new DateTimeBlock(this.start.plus(quantity, units), this.duration);
    }

    public String toString() {
        return "[start=" + this.start + "; duration=" + this.duration + "]";

    }

    private final LocalDateTime start;

    private final Duration duration;
}
