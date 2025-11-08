package com.next.script;

import com.next.core.model.session.GameSession;

import java.util.List;

public interface ScriptExecutor {
    void executeInstructions(List<Instruction> instructions, GameSession data);
}
