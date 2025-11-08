package com.next.script.implementation;

import com.next.exception.ExceptionHandler;
import com.next.script.Command;
import com.next.script.Instruction;
import com.next.script.ScriptParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ScriptParserImpl implements ScriptParser {

    private final Map<String, Command> commandByKey;

    public ScriptParserImpl() {
        commandByKey = new HashMap<>();
        commandByKey.put("\\text", Command.PRINT);
        commandByKey.put("\\waitInput", Command.WAIT);
        commandByKey.put("\\sleep", Command.SLEEP);
        commandByKey.put("\\input", Command.INPUT);
        commandByKey.put("\\begin-options", Command.SELECT);
        commandByKey.put("\\option", Command.OPTION);
        commandByKey.put("\\begin-case", Command.CASE);
        commandByKey.put("\\line", Command.NEW_LINE);
        commandByKey.put("\\clear", Command.CLEAR);
        commandByKey.put("\\set-var", Command.VAR);
        commandByKey.put("\\load-script", Command.CALL_SCRIPT);
    }

    @Override
    public List<Instruction> parseScript(InputStream scriptInput) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
        return parseBlock(reader);
    }

    private List<Instruction> parseBlock(BufferedReader reader) throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        String line;

        Command currentCommand = null;
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
                    Command blockCommand = getCommandByKeyword(parts[0]);
                    if (blockCommand != null) {
                        instructions.add(new Instruction(blockCommand, argument, blockInstructions));
                    }

                    currentCommand = null;
                } else {
                    // Start a new command
                    currentCommand = getCommandByKeyword(line);
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

    private Command getCommandByKeyword(String keyword) {
        if (commandByKey.containsKey(keyword)) {
            return commandByKey.get(keyword);
        }

        ExceptionHandler.handleError(keyword + " is not a valid command!");
        return null;
    }
}
