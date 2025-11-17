package com.next.core.rules.combat.implementation;

import com.next.core.model.session.GameSession;
import com.next.core.model.stats.Attributes;
import com.next.core.rules.combat.Battle;
import com.next.core.rules.combat.Combatant;

import java.util.*;

public class BattleImpl implements Battle {
    private final GameSession session;

    private List<Combatant> allies;
    private List<Combatant> enemies;

    public BattleImpl(GameSession session, List<Combatant> allies, List<Combatant> enemies) {
        this.session = session;
        this.allies = allies;
        this.enemies = enemies;
    }

    @Override
    public void resolveRound() {
        Stack<Combatant> stack = getRoundOrder();

        while (!stack.isEmpty()) {
            Combatant c = stack.pop();
            String order = c.getOrder();

            if (order.equals("strike")) {
                strike(c);
            }
        }
    }

    @Override
    public boolean isOver() {
        return false;
    }

    public Stack<Combatant> getRoundOrder() {
        Stack<Combatant> orderStack = new Stack<>();

        List<Combatant> combatants = new ArrayList<>();
        combatants.addAll(allies);
        combatants.addAll(enemies);

        Map<Integer, Combatant> byInitiative = getInitiative(combatants);

        for (Integer initiative : byInitiative.keySet()) {
            orderStack.push(byInitiative.get(initiative));
        }

        return orderStack;
    }

    public void strike(Combatant striker) {
        List<Combatant> foes;
        if (allies.contains(striker))
            foes = enemies;
        else
            foes = allies;

        int totalMenace = foes.stream().mapToInt(Combatant::getMenace).sum();
        int targetRoll = session.getRng().rollDice(totalMenace);

        Combatant target = foes.getFirst(); // calc to get a foe based on menace
        strike(striker, target);
    }

    public void strike(Combatant striker, Combatant target) {
        Attributes atkStats = striker.getCharacter().getAttributes();
        Attributes defStats = striker.getCharacter().getAttributes();

        int hitRoll = roll3Dices() + atkStats.getCombat();
        int defReflexes = (defStats.getSpeed() + defStats.getSenses()) / 2;
        int hitCalc = hitRoll - defReflexes;
        if (hitCalc < 0) {
            return; // dodges
        }

        hitCalc -= defStats.getCombat();
        if (hitCalc < 0) {
            return; // blocks
        }

        int rawDamage = striker.getAttackPower();
        int damage = rawDamage - target.getResistance();

        if (damage == 0) {
            return; // no damage
        }

        if (damage > 0) {
            int resultHp = target.getCharacter().getHealth() - damage;
            if (resultHp < 0) resultHp = 0;

            target.getCharacter().setHealth(resultHp);
        }
    }

    private Map<Integer, Combatant> getInitiative(List<Combatant> combatants) {
        Map<Integer, Combatant> initiatives = new TreeMap<>();
        for (int i = 0; i < combatants.size(); i++) {
            int diceRoll = roll3Dices();
            int initiative = combatants.get(i).getCharacter().getAttributes().getSpeed() + diceRoll;
            if (initiatives.containsKey(initiative)) {
                i--;
                continue;
            }
            initiatives.put(initiative, combatants.get(i));
        }

        return initiatives;
    }

    private int roll3Dices() {
        return session.getRng().rollDice(6) +
                session.getRng().rollDice(6) +
                session.getRng().rollDice(6);
    }
}
