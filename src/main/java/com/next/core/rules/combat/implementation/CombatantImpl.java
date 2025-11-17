package com.next.core.rules.combat.implementation;

import com.next.core.model.entities.Character;
import com.next.core.rules.combat.Combatant;

public class CombatantImpl extends Combatant {
    private String order;
    private int resistance;

    public CombatantImpl(Character character) {
        super(character);
    }

    @Override
    public String getOrder() {
        return "attack";
    }

    @Override
    public int getResistance() {
        int additionalRes = resistance;
        return additionalRes + character.getEquipment().getArmor().getResistance();
    }

    @Override
    public int getAttackPower() {
        int weaponMight = 10;   // get weapon might
        int expertiseDamage = 1;    // get expertise damage
        return weaponMight + expertiseDamage + character.getAttributes().getCombat();
    }
}
