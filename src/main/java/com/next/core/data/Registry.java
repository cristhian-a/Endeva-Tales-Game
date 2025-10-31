package com.next.core.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Registry<T extends Identifiable> {

    private final Map<String, T> CLASSES = new HashMap<>();

    public void register(T t) {
        CLASSES.put(t.getId(), t);
    }

    public T get(String id) {
        return CLASSES.get(id);
    }

    public void load(Collection<T> items) {
        items.forEach(this::register);
    }
}
