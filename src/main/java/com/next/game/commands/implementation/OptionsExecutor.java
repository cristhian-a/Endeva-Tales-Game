package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.graphics.TextPrinter;
import com.next.graphics.implementation.ConsolePrinter;
import com.next.io.InputReader;
import com.next.script.Command;
import com.next.script.Instruction;

import java.util.List;

public class OptionsExecutor implements CommandExecutor {

    private final InputReader inputReader;
    private final TextPrinter textPrinter;

    public OptionsExecutor(InputReader inputReader, TextPrinter textPrinter) {
        this.inputReader = inputReader;
        this.textPrinter = textPrinter;
    }

    @Override
    public void execute(Instruction instruction, GameSession gameSession) {
        int input;
        textPrinter.println();

        List<Instruction> subInstructions = instruction.getInstructions();
        for (int i = 0; i < subInstructions.size(); i++ ) {
            Instruction subInstruction = subInstructions.get(i);
            if (subInstruction.getCommand() == Command.OPTION) {
                String text = "\t[" + (i + 1) + "] " + subInstruction.getArgument() + "\n";
                textPrinter.typeQuickly(text);
            }
        }

        do {
            textPrinter.println();
            input = inputReader.readInt();

            if (input > subInstructions.size() || input < 1)
                textPrinter.println("Opção Inválida!");

        } while (input > subInstructions.size() || input < 1);

        gameSession.getContextData().put("input", String.valueOf(input));
    }
}
