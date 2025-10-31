package com.next.core.character;

import com.next.core.character.enums.ItemClass;
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
