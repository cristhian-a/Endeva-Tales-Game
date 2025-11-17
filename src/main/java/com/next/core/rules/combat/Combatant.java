package com.next.core.rules.combat;

import com.next.core.model.entities.Character;
import lombok.Data;

@Data
public abstract class Combatant {
    protected final Character character;
    protected int menace;

    public abstract String getOrder();
    public abstract int getResistance();
    public abstract int getAttackPower();
}
