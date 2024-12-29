package com.next.game.commands.implementation;

import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;

import java.util.Map;

public class TypeExecutor implements CommandExecutor {

    @Override
    public void execute(String arguments, Map<String, Object> context) {
        TextPrinter.typeText(arguments);
    }
}
