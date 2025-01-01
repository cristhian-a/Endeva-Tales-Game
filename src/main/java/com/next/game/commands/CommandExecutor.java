package com.next.game.commands;

import com.next.core.data.GameData;
import com.next.script.Instruction;

import java.util.Map;

public interface CommandExecutor {
    void execute(Instruction instruction, GameData data);
}
