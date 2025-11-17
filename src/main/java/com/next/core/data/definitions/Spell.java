package com.next.core.data.definitions;

import com.next.core.data.definitions.enums.EffectType;
import com.next.core.data.definitions.enums.SpellClass;
import com.next.core.data.registry.Identifiable;
import lombok.Data;

@Data
public class Spell implements Identifiable {
    private String id;
    private String name;
    private String description;
    private int complexity;
    private EffectType type;
    private SpellClass spellClass;

    @Override
    public String getId() {
        return this.id;
    }
}
