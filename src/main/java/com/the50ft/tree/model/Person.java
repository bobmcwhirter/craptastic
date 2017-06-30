package com.the50ft.tree.model;

import com.the50ft.tree.resources.AbstractResource;
import com.the50ft.tree.resources.Resource;

/**
 * Created by bob on 6/30/17.
 */
public class Person extends AbstractResource implements Resource {
    public Person(String id) {
        super(id);
    }
}
