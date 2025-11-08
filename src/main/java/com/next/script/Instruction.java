package com.next.script;

import lombok.Data;

import java.util.List;

@Data
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

    public Instruction(String command, String argument, List<Instruction> instructions) {
        this.command = command;
        this.argument = argument;
        this.instructions = instructions;
    }

}
