package com.next.script;

public class Instruction {
    private String command;
    private String value;

    public Instruction(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
