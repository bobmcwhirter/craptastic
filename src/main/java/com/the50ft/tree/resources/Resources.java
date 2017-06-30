package com.the50ft.tree.resources;

import java.util.stream.Stream;

/**
 * Created by bob on 6/23/17.
 */
public interface Resources<T extends Resource> {
    T get(String id);
    Stream<T> all();
    Resources<T> refine(String...qualifiers);
}
