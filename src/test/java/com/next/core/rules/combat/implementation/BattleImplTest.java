package com.next.core.rules.combat.implementation;

import com.next.core.model.entities.Character;
import com.next.core.model.session.GameSession;
import com.next.core.model.stats.Attributes;
import com.next.core.rules.combat.Combatant;
import com.next.util.PCG32;
import com.next.util.Rng;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BattleImplTest {

    @Test
    void testInitiative() {
        Combatant[] allies = generateCombatants("Ally");
        Combatant[] enemies = generateCombatants("Enemy");

        Rng rng = new PCG32(1234567890, 1);
        BattleImpl battle = new BattleImpl(new GameSession(rng), List.of(allies), List.of(enemies));
        var roundOrder = battle.getRoundOrder();

        assert roundOrder.size() == 6;
        assert roundOrder.pop().getCharacter().getName().equals("Bento Ally");
        assert roundOrder.pop().getCharacter().getName().equals("Bento Enemy");
        assert roundOrder.pop().getCharacter().getName().equals("Rodolfo Enemy");
        assert roundOrder.pop().getCharacter().getName().equals("Ernesto Enemy");
        assert roundOrder.pop().getCharacter().getName().equals("Ernesto Ally");
        assert roundOrder.pop().getCharacter().getName().equals("Rodolfo Ally");
    }

    private Combatant[] generateCombatants(String id) {
        Combatant[] combatants = new Combatant[3];
        Character ch1 = new Character();
        Attributes a1 = new Attributes();
        ch1.setName("Rodolfo " + id);
        ch1.setAttributes(a1);
        a1.setSpeed(5);
        Combatant c1 = new CombatantImpl(ch1);

        Character ch2 = new Character();
        Attributes a2 = new Attributes();
        ch2.setName("Ernesto " + id);
        ch2.setAttributes(a2);
        a2.setSpeed(7);
        Combatant c2 = new CombatantImpl(ch2);

        Character ch3 = new Character();
        Attributes a3 = new Attributes();
        ch3.setName("Bento " + id);
        ch3.setAttributes(a3);
        a3.setSpeed(9);
        Combatant c3 = new CombatantImpl(ch3);

        combatants[0] = c1;
        combatants[1] = c2;
        combatants[2] = c3;

        return combatants;
    }
}
