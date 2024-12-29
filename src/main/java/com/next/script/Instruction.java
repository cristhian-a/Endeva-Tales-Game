package com.next.script;

public class Instruction {
    private final String command;
    private String argument;

    public Instruction(String command) {
        this.command = command;
    }

    public Instruction(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
