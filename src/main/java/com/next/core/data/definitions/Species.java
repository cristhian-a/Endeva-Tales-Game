package com.next.core.data.definitions;

import com.next.core.data.registry.Identifiable;
import lombok.Data;

@Data
public class Species implements Identifiable {
    private String id;
    private String name;
}
