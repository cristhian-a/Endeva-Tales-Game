package com.next.core.character;

public class Attributes {
    private int body;
    private int agility;
    private int senses;
    private int mind;
    private int spirit;
    private int charisma;

    public Attributes() {
    }

    public Attributes(int body, int agility, int senses, int mind, int spirit, int charisma) {
        this.body = body;
        this.agility = agility;
        this.senses = senses;
        this.mind = mind;
        this.spirit = spirit;
        this.charisma = charisma;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getSenses() {
        return senses;
    }

    public void setSenses(int senses) {
        this.senses = senses;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
}
