package com.the50ft.tree.schedule;

import java.time.Duration;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.time.DateTimeBlock;

/**
 * Created by bob on 6/23/17.
 */
public interface Schedule<T extends Resource> {

    T getResource();

    boolean isAvailable(DateTimeBlock block);

    Duration availabilityBefore(DateTimeBlock block);

    Duration availabilityAfter(DateTimeBlock block);

}
