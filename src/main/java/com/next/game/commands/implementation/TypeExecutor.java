package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.graphics.implementation.ConsolePrinter;
import com.next.script.Instruction;

public class TypeExecutor implements CommandExecutor {

    private final TextPrinter printer;

    public TypeExecutor (TextPrinter textPrinter) {
        this.printer = textPrinter;
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        printer.type(instruction.getArgument());
    }
}
