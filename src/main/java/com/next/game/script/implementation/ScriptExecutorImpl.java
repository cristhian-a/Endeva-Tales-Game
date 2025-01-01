package com.next.game.script.implementation;

import com.next.core.data.GameData;
import com.next.exception.ExceptionHandler;
import com.next.game.commands.CommandExecutor;
import com.next.game.commands.implementation.ContinueExecutor;
import com.next.game.commands.implementation.InputExecutor;
import com.next.game.commands.implementation.OptionsExecutor;
import com.next.game.commands.implementation.TypeExecutor;
import com.next.game.script.ScriptExecutor;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptExecutorImpl implements ScriptExecutor {

    private final Map<String, CommandExecutor> executors = new HashMap<>();

    public ScriptExecutorImpl() {
        this.executors.put("\\text", new TypeExecutor());
        this.executors.put("\\waitInput", new ContinueExecutor());
        this.executors.put("\\input", new InputExecutor());
        this.executors.put("\\begin-options", new OptionsExecutor(new InputReader()));
    }

    @Override
    public void executeInstructions(List<Instruction> instructions, GameData data) {
        for (Instruction i : instructions) {
            executeInstruction(i, data);
        }
    }

    private void executeInstruction(Instruction instruction, GameData data) {
        String command = instruction.getCommand();

        CommandExecutor executor = executors.get(command);
        if (executor == null) {
            ExceptionHandler.handleError(command + " não é um comando válido!");
        } else {
            executor.execute(instruction, data);
        }
    }
}
