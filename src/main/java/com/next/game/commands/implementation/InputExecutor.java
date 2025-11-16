package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.graphics.implementation.ConsolePrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

public class InputExecutor implements CommandExecutor {

    private final InputReader inputReader;
    private final TextPrinter textPrinter;

    public InputExecutor(InputReader inputReader, TextPrinter textPrinter) {
        this.inputReader = inputReader;
        this.textPrinter = textPrinter;
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        if (instruction.getArgument() != null && !instruction.getArgument().isBlank()) {
            textPrinter.println();
            textPrinter.typeQuickly(instruction.getArgument());
            textPrinter.println();
        }

        String val = inputReader.read();
        gameSession.getContextData().put("input", val);
    }
}
