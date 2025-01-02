package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.script.Instruction;

public class NewLineExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameData data) {
        TextPrinter.newLine();
    }
}
