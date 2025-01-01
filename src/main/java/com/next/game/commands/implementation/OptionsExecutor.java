package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OptionsExecutor implements CommandExecutor {

    private final InputReader inputReader;

    public OptionsExecutor(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public void execute(Instruction instruction, GameData gameData) {
        TextPrinter.println("");

        List<Instruction> subInstructions = instruction.getInstructions();
        for (int i = 0; i < subInstructions.size(); i++ ) {
            Instruction subInstruction = subInstructions.get(i);
            if (subInstruction.getCommand().equals("\\option")) {
                String text = "\t[" + (i + 1) + "] " + subInstruction.getArgument() + "\n";
                TextPrinter.typeTextQuickly(text);
            }
        }

        TextPrinter.println("");
        String input = inputReader.read();
        gameData.getContextData().put("input", input);
    }
}
