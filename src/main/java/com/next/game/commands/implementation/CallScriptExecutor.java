package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.io.FileReader;
import com.next.script.Instruction;
import com.next.script.ScriptExecutor;
import com.next.script.ScriptParser;

import java.io.IOException;
import java.util.List;

public class CallScriptExecutor implements CommandExecutor {

    private final ScriptParser parser;
    private final ScriptExecutor executor;

    public CallScriptExecutor(ScriptParser parser, ScriptExecutor executor) {
        this.parser = parser;
        this.executor = executor;
    }

    @Override
    public void execute(Instruction instruction, GameSession data) {
        String path = "adventures/scripts/" + data.getAdventureData().pathKey + "/" + instruction.getArgument();
        var inputStream = FileReader.readFile(path);

        try {
            List<Instruction> instructions = parser.parseScript(inputStream);
            executor.executeInstructions(instructions, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
