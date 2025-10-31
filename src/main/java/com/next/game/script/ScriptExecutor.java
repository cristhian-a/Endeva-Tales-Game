package com.next.game.script;

import com.next.core.model.session.GameSession;
import com.next.script.Instruction;

import java.util.List;

public interface ScriptExecutor {
    void executeInstructions(List<Instruction> instructions, GameSession data);
}
