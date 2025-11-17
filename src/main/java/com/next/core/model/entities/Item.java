package com.next.core.model.entities;

import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private String description;
    private int weight;

    public boolean isTwoHanded() {
        // TO DO
        // verify in its properties if item is two-handed
        return false;
    }
}
