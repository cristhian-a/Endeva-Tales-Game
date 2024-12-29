package com.next.game.commands.implementation;

import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;

import java.util.Map;

public class InputExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public InputExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(String arguments, Map<String, Object> context) {
        if (arguments != null && !arguments.isBlank()) {
            TextPrinter.println("");
            TextPrinter.typeTextQuickly(arguments);
            TextPrinter.println("");
        }

        String val = inputReader.read();
        context.put("input", val);
    }
}
