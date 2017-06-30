package com.the50ft.tree.schedule.memory;

import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
public abstract class AbstractDateTimeAvailability implements Availability {

    protected AbstractDateTimeAvailability(DateTimeBlock block) {
        this.block = block;
    }

    protected DateTimeBlock getBlock() {
        return this.block;
    }

    private final DateTimeBlock block;
}
