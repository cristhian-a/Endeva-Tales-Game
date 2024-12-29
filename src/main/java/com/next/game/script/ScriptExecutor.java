package com.next.game.script;

import com.next.script.Instruction;

import java.util.List;
import java.util.Map;

public interface ScriptExecutor {
    void executeInstructions(List<Instruction> instructions, Map<String, Object> context);
}
