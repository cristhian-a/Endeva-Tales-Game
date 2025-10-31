package com.next.core.model.entities;

import com.next.core.data.definitions.CharacterClass;
import com.next.core.data.definitions.Species;
import com.next.core.data.definitions.enums.ItemClass;
import com.next.core.model.stats.Attributes;
import com.next.core.model.stats.Expertise;
import lombok.Data;

import java.util.List;

@Data
public class Character {
    private String name;
    private int maxHealth;
    private int health;
    private int exp;
    private CharacterClass clazz;
    private Species species;
    private Attributes attributes;
    private List<Item> inventory;
    private Expertise[] expertises;

    public Character() {
        this.expertises =  new Expertise[ItemClass.values().length];
    }
}
