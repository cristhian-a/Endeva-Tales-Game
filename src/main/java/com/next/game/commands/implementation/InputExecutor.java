package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.Map;

public class InputExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public InputExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(Instruction instruction, GameData gameData) {
        if (instruction.getArgument() != null && !instruction.getArgument().isBlank()) {
            TextPrinter.println("");
            TextPrinter.typeTextQuickly(instruction.getArgument());
            TextPrinter.println("");
        }

        String val = inputReader.read();
        gameData.getContextData().put("input", val);
    }
}
