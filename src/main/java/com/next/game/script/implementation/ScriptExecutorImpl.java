package com.next.game.script.implementation;

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
    public void executeInstructions(List<Instruction> instructions, Map<String, Object> context) {
        for (Instruction i : instructions) {
            executeInstruction(i, context);
        }
    }

    private void executeInstruction(Instruction instruction, Map<String, Object> context) {
        String command = instruction.getCommand();
        String arguments = instruction.getArgument();

        CommandExecutor executor = executors.get(command);
        if (executor == null) {
            ExceptionHandler.handleError(command + " não é um comando válido!");
        } else {
            executor.execute(arguments, context);
        }
    }
}
