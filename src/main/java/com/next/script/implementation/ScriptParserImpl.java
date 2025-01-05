package com.next.script.implementation;

import com.next.script.Instruction;
import com.next.script.ScriptParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ScriptParserImpl implements ScriptParser {

//    @Override
//    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
//        List<Instruction> instructions = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
//        String line;
//
//        Stack<String> blockStack = new Stack<>();
//
//        String currentCommand = null;
//        StringBuilder currentArguments = new StringBuilder();
//
//        while ((line = reader.readLine()) != null) {
//            if (line.startsWith("--")) {
//                continue;   // Skips comments
//            } else if (line.startsWith("\\end-")) {
//                // TO DO
//                if (blockStack.isEmpty()) {
//                    throw new IllegalStateException("Unmatched \\end command: " + line);
//                }
//
//                String blockName = line.substring(5);
//                String expectedBlockName = blockStack.pop();
//
//                if (!blockName.equals(expectedBlockName)) {
//                    throw new IllegalStateException("Mismatched block names: \\begin-" + expectedBlockName + " and \\end-" + blockName);
//                }
//
//                instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
//                currentCommand = null;
//                currentArguments.setLength(0);
//            } else if (!blockStack.isEmpty()) {
//                currentArguments.append(line).append("\n");
//            } else if (line.startsWith("\\begin-")) {
//                // TO DO
//                if (currentCommand != null) {
//                    instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
//                }
//
//                String blockName = line.substring(7);
//                blockStack.push(blockName);
//
//                currentCommand = line;
//                currentArguments.setLength(0); // Clear the arguments buffer
//            } else if (line.startsWith("\\")) {
//                // If there's a current command, add it to the instructions
//                if (currentCommand != null) {
//                    instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
//                }
//
//                // Start a new command
//                currentCommand = line;
//                currentArguments.setLength(0); // Clear the arguments buffer
//            } else {
//                // Accumulate arguments for the current command
//                currentArguments.append(line).append("\n");
//            }
//        }
//
//        // Add the last command
//        if (currentCommand != null) {
//            instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
//        }
//
//        return instructions;
//    }

    @Override
    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
        return parseBlock(reader);
    }

    private List<Instruction> parseBlock(BufferedReader reader) throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        String line;

        String currentCommand = null;
        StringBuilder currentArguments = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("--")) {
                continue;
            } else if (line.startsWith("\\end-")) {
                break;
            } else if (line.startsWith("\\")) {
                // If there's a current command, add it to the instructions
                if (currentCommand != null) {
                    instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
                }

                // If a new block is denoted, create its sub instructions
                if (line.startsWith("\\begin-")) {
                    // Splitting into command and arguments passed in the block
                    String[] parts = line.split(":");

                    String argument = null;
                    if (parts.length > 1) {
                        argument = parts[1];
                    }
                    var blockInstructions = parseBlock(reader);
                    instructions.add(new Instruction(parts[0], argument, blockInstructions));

                    currentCommand = null;
                } else {
                    // Start a new command
                    currentCommand = line;
                }

                currentArguments.setLength(0); // Clear the arguments buffer
            } else {
                // Accumulate arguments for the current command
                currentArguments.append(line).append("\n");
            }
        }

        // Add the last command
        if (currentCommand != null) {
            instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
        }

        return instructions;
    }
}
