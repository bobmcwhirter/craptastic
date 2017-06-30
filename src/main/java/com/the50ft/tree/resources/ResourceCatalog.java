package com.the50ft.tree.resources;

/**
 * Created by bob on 6/21/17.
 */
public interface ResourceCatalog {
    <T extends Resource> Resources<T> of(Class<T> cls, String... qualifiers);
}
