package com.the50ft.tree.schedule.memory;

import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
class DateTimeUnavailability extends AbstractDateTimeAvailability {
    DateTimeUnavailability(DateTimeBlock block) {
        super(block);
    }

    @Override
    public Status test(DateTimeBlock block) {
        if ( getBlock().overlaps(block) ) {
            return Status.UNAVAILABLE;
        }

        return Status.UNKNOWN;
    }
}
