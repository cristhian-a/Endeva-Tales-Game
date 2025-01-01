package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.Map;

public class ContinueExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public ContinueExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(Instruction instruction, GameData gameData) {
        TextPrinter.typeTextQuickly("\nPressione ENTER para continuar!");
        inputReader.read();
    }
}
