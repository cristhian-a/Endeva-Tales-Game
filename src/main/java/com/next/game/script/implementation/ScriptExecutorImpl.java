package com.next.game.script.implementation;

import com.next.core.data.GameData;
import com.next.exception.ExceptionHandler;
import com.next.game.commands.CommandExecutor;
import com.next.game.commands.implementation.*;
import com.next.game.script.ScriptExecutor;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptExecutorImpl implements ScriptExecutor {

    private final Map<String, CommandExecutor> executors = new HashMap<>();

    public ScriptExecutorImpl() {
        executors.put("\\text", new TypeExecutor());
        executors.put("\\waitInput", new ContinueExecutor());
        executors.put("\\input", new InputExecutor());
        executors.put("\\begin-options", new OptionsExecutor(new InputReader()));
        executors.put("\\begin-case", new CaseExecutor(this));
        executors.put("\\line", new NewLineExecutor());
        executors.put("\\clear", new ClearConsoleExecutor());
        executors.put("\\sleep", new SleepExecutor());
        executors.put("\\set-var", new SetVarExecutor());
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
