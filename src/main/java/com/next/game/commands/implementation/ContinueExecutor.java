package com.next.game.commands.implementation;

import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;

import java.util.Map;

public class ContinueExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public ContinueExecutor() {
        this.inputReader = new InputReader();
    }

    @Override
    public void execute(String arguments, Map<String, Object> context) {
        TextPrinter.typeTextQuickly("\nPressione ENTER para continuar!");
        inputReader.read();
    }
}
