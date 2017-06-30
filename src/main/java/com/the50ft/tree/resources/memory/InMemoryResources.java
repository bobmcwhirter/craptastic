package com.the50ft.tree.resources.memory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.resources.Resources;

/**
 * Created by bob on 6/23/17.
 */
class InMemoryResources<T extends Resource> implements Resources<T> {

    InMemoryResources(InMemoryResourceCatalog catalog, Class<T> cls, String...qualifiers) {
        this( catalog, cls, new HashSet<String>() {{
            addAll( Arrays.asList(qualifiers));
        }});
    }

    InMemoryResources(InMemoryResourceCatalog catalog, Class<T> cls, Set<String> qualifiers) {
        this.catalog = catalog;
        this.cls = cls;
        this.qualifiers = qualifiers;
    }

    @Override
    public T get(String id) {
        return all()
                .filter(e -> e.id().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Stream<T> all() {
        return this.catalog.all(this.cls, qualifiers);
    }

    @Override
    public Resources<T> refine(String... qualifiers) {
        Set<String> newQualifiers = new HashSet<>();
        newQualifiers.addAll(this.qualifiers);
        newQualifiers.addAll(Arrays.asList(qualifiers));

        return new InMemoryResources<>(this.catalog, this.cls, newQualifiers);
    }

    public String toString() {
        return "[" + this.cls.getSimpleName() + this.qualifiers + "]";
    }

    private final InMemoryResourceCatalog catalog;

    private final Class<T> cls;

    private final Set<String> qualifiers;

}
