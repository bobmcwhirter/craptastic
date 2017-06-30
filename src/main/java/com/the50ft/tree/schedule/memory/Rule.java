package com.the50ft.tree.schedule.memory;

import com.the50ft.tree.time.DateTimeBlock;
import com.the50ft.tree.time.DayTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
public class Rule implements Availability {

    public Rule(Status type, DayTimeBlock block) {
        this.type = type;
        this.block = block;
    }

    public Status test(DateTimeBlock block) {
        if (block.getStart().getDayOfWeek() != this.block.getDay()) {
            return Status.UNKNOWN;
        }

        if (this.type == Status.AVAILABLE) {
            if (this.block.contains(block)) {
                return Status.AVAILABLE;
            } else {
                return Status.UNKNOWN;
            }
        } else {
            if (this.block.overlaps(block)) {
                return Status.UNAVAILABLE;
            } else {
                return Status.UNKNOWN;
            }
        }
    }

    private final Status type;

    private final DayTimeBlock block;
}
