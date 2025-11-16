package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.graphics.implementation.ConsolePrinter;
import com.next.script.Instruction;

public class ClearConsoleExecutor implements CommandExecutor {

    private final TextPrinter textPrinter;

    public ClearConsoleExecutor(TextPrinter textPrinter) {
        this.textPrinter = textPrinter;
    }

    @Override
    public void execute(Instruction instruction, GameSession data) {
        textPrinter.clear();
    }
}
