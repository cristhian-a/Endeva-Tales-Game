package com.next.game.commands;

import com.next.core.model.session.GameSession;
import com.next.script.Instruction;

public interface CommandExecutor {
    void execute(Instruction instruction, GameSession data);
}
