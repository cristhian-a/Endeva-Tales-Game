package com.next.game.commands.implementation;

import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OptionsExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public OptionsExecutor(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String arguments, Map<String, Object> context) {
        TextPrinter.println("");

        String[] splitByOption = arguments.split("\\\\option");
        List<String> options = Arrays.stream(splitByOption).filter(x -> !x.isBlank()).toList();
        for (int i = 0; i < options.size(); i++) {
            String text = "\t[" + (i + 1) + "] " + options.get(i) + "\n";
            TextPrinter.typeTextQuickly(text);
        }

        TextPrinter.println("");
        String input = inputReader.read();
        context.put("input", input);
    }
}
