package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

public class InputExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public InputExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        if (instruction.getArgument() != null && !instruction.getArgument().isBlank()) {
            TextPrinter.println("");
            TextPrinter.typeTextQuickly(instruction.getArgument());
            TextPrinter.println("");
        }

        String val = inputReader.read();
        gameSession.getContextData().put("input", val);
    }
}
