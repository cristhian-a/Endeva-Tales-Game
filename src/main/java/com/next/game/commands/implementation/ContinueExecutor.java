package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

public class ContinueExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public ContinueExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        TextPrinter.typeTextQuickly("\nPressione ENTER para continuar!");
        inputReader.read();
    }
}
