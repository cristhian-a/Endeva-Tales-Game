package com.next.game.commands.implementation;

import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;

import java.util.Map;

public class OptionsExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public OptionsExecutor(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String arguments, Map<String, Object> context) {
        String[] options = arguments.split("\n");
        for (String s : options) {
            TextPrinter.typeText(s);
        }
    }
}
