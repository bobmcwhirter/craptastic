package com.the50ft.tree.schedule.memory;

import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
class DateTimeAvailability extends AbstractDateTimeAvailability {

    DateTimeAvailability(DateTimeBlock block) {
        super( block );
    }

    @Override
    public Status test(DateTimeBlock block) {
        if ( getBlock().contains( block ) ) {
            return Status.AVAILABLE;
        }

        return Status.UNKNOWN;
    }

}
