package com.next.script;

import java.util.List;

public class Instruction {
    private final String command;
    private String argument;
    private List<Instruction> instructions;

    public Instruction(String command) {
        this.command = command;
    }

    public Instruction(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public Instruction(String command, List<Instruction> instructions) {
        this.command = command;
        this.instructions = instructions;
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

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
