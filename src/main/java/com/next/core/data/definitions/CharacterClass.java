package com.next.core.data.definitions;

import com.next.core.data.registry.Identifiable;
import lombok.Data;

@Data
public class CharacterClass implements Identifiable {
    private final String id;
    private String name;
}
