package com.next.script.implementation;

import com.next.script.ScriptParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriptParserImpl implements ScriptParser {

    @Override
    public List<Map.Entry<String, String>> parseScript(InputStream scriptInput) throws IOException {
        List<Map.Entry<String, String>> instructions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(scriptInput));
        String line;

        String currentCommand = null;
        StringBuilder currentArguments = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("--")) {
                continue;   // Skips comments
            }
            if (line.startsWith("\\")) {
                // If there's a current command, add it to the instructions
                if (currentCommand != null) {
                    instructions.add(Map.entry(currentCommand, currentArguments.toString().trim()));
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
            instructions.add(Map.entry(currentCommand, currentArguments.toString().trim()));
        }

        return instructions;
    }
}
