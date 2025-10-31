package com.next.core.character;

import com.next.core.data.Identifiable;
import lombok.Data;

@Data
public class CharacterClass implements Identifiable {
    private final String id;
    private String name;
}
