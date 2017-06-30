package com.the50ft.tree.resources;

/**
 * Created by bob on 6/26/17.
 */
public class AbstractResource implements Resource {

    protected AbstractResource(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return this.id;
    }

    public String toString() {
        return "[" + this.getClass().getSimpleName() + "(" + this.id + ")]";
    }

    private final String id;
}
