package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.script.Instruction;

import java.util.Map;

public class TypeExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameData gameData) {
        TextPrinter.typeText(instruction.getArgument());
    }
}
