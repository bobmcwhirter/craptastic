package com.the50ft.tree.schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
public interface MutableSchedule<T extends Resource> extends Schedule<T> {
    void available(LocalTime start, LocalTime end, DayOfWeek... days);

    void unavailable(LocalTime start, LocalTime end, DayOfWeek... days);

    void available(DateTimeBlock block);

    void unavailable(DateTimeBlock block);
}
