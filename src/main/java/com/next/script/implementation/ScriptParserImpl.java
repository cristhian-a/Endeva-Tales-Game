package com.next.script.implementation;

import com.next.script.Instruction;
import com.next.script.ScriptParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ScriptParserImpl implements ScriptParser {

    @Override
    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
        String line;

        Stack<String> blockStack = new Stack<>();

        String currentCommand = null;
        StringBuilder currentArguments = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("--")) {
                continue;   // Skips comments
            } else if (line.startsWith("\\end-")) {
                // TO DO
                if (blockStack.isEmpty()) {
                    throw new IllegalStateException("Unmatched \\end command: " + line);
                }

                String blockName = line.substring(5);
                String expectedBlockName = blockStack.pop();

                if (!blockName.equals(expectedBlockName)) {
                    throw new IllegalStateException("Mismatched block names: \\begin-" + expectedBlockName + " and \\end-" + blockName);
                }

                instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
                currentCommand = null;
                currentArguments.setLength(0);
            } else if (!blockStack.isEmpty()) {
                currentArguments.append(line).append("\n");
            } else if (line.startsWith("\\begin-")) {
                // TO DO
                if (currentCommand != null) {
                    instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
                }

                String blockName = line.substring(7);
                blockStack.push(blockName);

                currentCommand = line;
                currentArguments.setLength(0); // Clear the arguments buffer
            } else if (line.startsWith("\\")) {
                // If there's a current command, add it to the instructions
                if (currentCommand != null) {
                    instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
                }

                // Start a new command
                currentCommand = line;
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

//    @Override
//    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
//        List<Instruction> instructions = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
//        String line;
//
//        Deque<String> blockStack = new ArrayDeque<>();
//        StringBuilder currentArguments = new StringBuilder();
//        String currentCommand = null;
//
//        while ((line = reader.readLine()) != null) {
//            line = line.trim(); // Remove leading/trailing whitespace
//
//            if (line.startsWith("\\begin-")) {
//                // Start a block
//                if (currentCommand != null) {
//                    throw new IllegalStateException("Nested block detected without ending the previous block: " + line);
//                }
//                blockStack.push(line); // Push the block name onto the stack
//                currentCommand = line;
//                currentArguments.setLength(0); // Clear arguments for the new block
//            } else if (line.startsWith("\\end-")) {
//                // End a block
//                if (blockStack.isEmpty()) {
//                    throw new IllegalStateException("Unmatched block ending: " + line);
//                }
//                String expectedEnd = "\\end-" + blockStack.pop().substring(7); // Extract block name
//                if (!line.equals(expectedEnd)) {
//                    throw new IllegalStateException("Mismatched block ending. Expected: " + expectedEnd + ", Found: " + line);
//                }
//
//                // Add the completed block to instructions
//                instructions.add(new Instruction(currentCommand, currentArguments.toString().trim()));
//                currentCommand = null;
//                currentArguments.setLength(0);
//            } else if (line.startsWith("\\")) {
//                // Handle single-line commands
//                if (currentCommand != null) {
//                    throw new IllegalStateException("Encountered a command inside a block without closing the block: " + line);
//                }
//                instructions.add(new Instruction(line, "")); // Single-line commands have no arguments
//            } else if (currentCommand != null) {
//                // Accumulate block content
//                currentArguments.append(line).append("\n");
//            }
//        }
//
//        // Handle unclosed blocks
//        if (!blockStack.isEmpty()) {
//            throw new IllegalStateException("Unclosed block: " + blockStack.peek());
//        }
//
//        return instructions;
//    }

//    @Override
//    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
//        List<Instruction> instructions = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
//
//        Stack<String> blockStack = new Stack<>();
//        StringBuilder currentContent = new StringBuilder();
//        String currentCommand = null;
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            line = line.trim(); // Remove leading/trailing whitespace
//
//            if (line.startsWith("\\begin-")) {
//                // Start a new block
//                if (currentCommand != null) {
//                    // Add the current command with its accumulated arguments
//                    instructions.add(new Instruction(currentCommand, currentContent.toString().trim()));
//                    currentContent.setLength(0); // Clear for the new block
//                }
//
//                String blockName = line.substring(7); // Extract block name after "\begin-"
//                blockStack.push(blockName);
//                currentCommand = line; // Use the entire \begin-[blockName] as the command
//            } else if (line.startsWith("\\end-")) {
//                if (blockStack.isEmpty()) {
//                    throw new IllegalStateException("Unmatched \\end command: " + line);
//                }
//
//                String blockName = line.substring(5); // Extract block name after "\end-"
//                String expectedBlockName = blockStack.pop();
//
//                if (!blockName.equals(expectedBlockName)) {
//                    throw new IllegalStateException("Mismatched block names: \\begin-" + expectedBlockName + " and \\end-" + blockName);
//                }
//
//                if (currentCommand != null) {
//                    // Add the completed block content
//                    instructions.add(new Instruction(currentCommand, currentContent.toString().trim()));
//                    currentCommand = null;
//                    currentContent.setLength(0);
//                }
//            } else if (line.startsWith("\\")) {
//                // Handle a new single-line command
//                if (currentCommand != null) {
//                    // Store the previous command and its accumulated arguments
//                    instructions.add(new Instruction(currentCommand, currentContent.toString().trim()));
//                    currentContent.setLength(0); // Clear content for the new command
//                }
//
//                currentCommand = line; // Set the new command
//            } else {
//                // Accumulate content for the current command or block
//                if (currentCommand != null) {
//                    currentContent.append(line).append("\n");
//                }
//            }
//        }
//
//        // Add any remaining command and its content
//        if (currentCommand != null) {
//            instructions.add(new Instruction(currentCommand, currentContent.toString().trim()));
//        }
//
//        // Check for unclosed blocks
//        if (!blockStack.isEmpty()) {
//            throw new IllegalStateException("Unclosed block: \\begin-" + blockStack.peek());
//        }
//
//        return instructions;
//    }
}
