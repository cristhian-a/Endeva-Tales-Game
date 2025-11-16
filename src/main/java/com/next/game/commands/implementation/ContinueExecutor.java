package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.graphics.implementation.ConsolePrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

public class ContinueExecutor implements CommandExecutor {

    private final InputReader inputReader;
    private final TextPrinter textPrinter;

    public ContinueExecutor(InputReader inputReader, TextPrinter textPrinter) {
        this.inputReader = inputReader;
        this.textPrinter = textPrinter;
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        textPrinter.typeQuickly("\nPressione ENTER para continuar!");
        inputReader.read();
    }
}
