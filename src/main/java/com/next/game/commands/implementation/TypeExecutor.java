package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.script.Instruction;

public class TypeExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        TextPrinter.typeText(instruction.getArgument());
    }
}
