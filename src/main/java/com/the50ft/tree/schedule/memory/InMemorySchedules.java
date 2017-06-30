package com.the50ft.tree.schedule.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.schedule.Schedule;
import com.the50ft.tree.schedule.Schedules;

/**
 * Created by bob on 6/23/17.
 */
public class InMemorySchedules implements Schedules {

    @Override
    public <T extends Resource> InMemorySchedule<T> of(T resource) {
        InMemorySchedule<T> schedule = (InMemorySchedule<T>) this.schedules.get(resource );
        if ( schedule == null ) {
            schedule = new InMemorySchedule<T>(resource);
            this.schedules.put( resource, schedule );
        }
        return schedule;
    }

    @Override
    public Stream<? extends Schedule> all() {
        return this.schedules.values().stream();
    }

    private Map<Resource, InMemorySchedule<?>> schedules = new HashMap<>();
}
