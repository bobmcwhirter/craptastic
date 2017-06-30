package com.the50ft.tree.schedule;

import java.util.stream.Stream;

import com.the50ft.tree.resources.Resource;

/**
 * Created by bob on 6/23/17.
 */
public interface Schedules {
    <T extends Resource> Schedule<T> of(T resource);

    Stream<? extends Schedule> all();
}
