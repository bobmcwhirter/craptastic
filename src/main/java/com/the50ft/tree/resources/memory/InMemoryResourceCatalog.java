package com.the50ft.tree.resources.memory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.the50ft.tree.resources.Resource;
import com.the50ft.tree.resources.ResourceCatalog;
import com.the50ft.tree.resources.Resources;

/**
 * Created by bob on 6/23/17.
 */
public class InMemoryResourceCatalog implements ResourceCatalog {

    @Override
    public <T extends Resource> Resources of(Class<T> cls, String... qualifiers) {
        return new InMemoryResources(this, cls, qualifiers);
    }

    public <T extends Resource> T add(Class<T> cls, T resource, String... qualifiers) {
        Set<ResourceRecord<? extends Resource>> set = this.resources.get(cls);
        if (set == null) {
            set = new HashSet<>();
            this.resources.put(cls, set);
        }

        Optional<ResourceRecord<? extends Resource>> searchResult = set.stream()
                .filter(e -> e.getResource() == resource)
                .findFirst();

        ResourceRecord<? extends Resource> record;

        if (searchResult.isPresent()) {
            record = searchResult.get();
        } else {
            record = new ResourceRecord<>(resource);
            set.add(record);
        }

        for (String qualifier : qualifiers) {
            record.addQualifier(qualifier);
        }

        return resource;
    }

    <T extends Resource> Stream<T> all(Class<T> cls, Set<String> qualifiers) {
        Set<ResourceRecord<? extends Resource>> set = this.resources.get(cls);
        if (set == null) {
            return Stream.empty();
        }

        Stream<T> result = set.stream()
                .filter(e -> e.hasQualifiers(qualifiers))
                .map(e -> (T) e.getResource());

        return result;
    }

    private Map<Class<? extends Resource>, Set<ResourceRecord<? extends Resource>>> resources = new HashMap<>();

    private static class ResourceRecord<T extends Resource> {
        ResourceRecord(T resource) {
            this.resource = resource;
        }

        Resource getResource() {
            return this.resource;
        }

        void addQualifier(String qualifier) {
            this.qualifiers.add(qualifier);
        }

        boolean hasQualifiers(Collection<String> qualifiers) {
            return this.qualifiers.containsAll(qualifiers);
        }

        Set<String> getQualifiers() {
            return this.qualifiers;
        }

        private final T resource;

        private final Set<String> qualifiers = new HashSet<>();
    }

}
