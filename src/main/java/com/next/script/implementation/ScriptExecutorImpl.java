package com.next.script.implementation;

import com.next.core.model.session.GameSession;
import com.next.exception.ExceptionHandler;
import com.next.game.commands.CommandExecutor;
import com.next.game.commands.implementation.*;
import com.next.script.Command;
import com.next.script.ExecutorDependencies;
import com.next.script.ScriptExecutor;
import com.next.io.InputReader;
import com.next.script.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptExecutorImpl implements ScriptExecutor {

    private final Map<Command, CommandExecutor> executors = new HashMap<>();

    public ScriptExecutorImpl(ExecutorDependencies dependencies) {
        executors.put(Command.PRINT, new TypeExecutor());
        executors.put(Command.WAIT, new ContinueExecutor());
        executors.put(Command.INPUT, new InputExecutor());
        executors.put(Command.SELECT, new OptionsExecutor(new InputReader()));
        executors.put(Command.CASE, new CaseExecutor(this));
        executors.put(Command.NEW_LINE, new NewLineExecutor());
        executors.put(Command.CLEAR, new ClearConsoleExecutor());
        executors.put(Command.SLEEP, new SleepExecutor());
        executors.put(Command.VAR, new SetVarExecutor());
        executors.put(Command.CALL_SCRIPT, new CallScriptExecutor(dependencies.getScriptParser(), this));
    }

    @Override
    public void executeInstructions(List<Instruction> instructions, GameSession data) {
        for (Instruction i : instructions) {
            executeInstruction(i, data);
        }
    }

    private void executeInstruction(Instruction instruction, GameSession data) {
        Command command = instruction.getCommand();

        CommandExecutor executor = executors.get(command);
        if (executor == null) {
            ExceptionHandler.handleError("Command " + command + " is not mapped in the interpreter!");
        } else {
            executor.execute(instruction, data);
        }
    }
}
