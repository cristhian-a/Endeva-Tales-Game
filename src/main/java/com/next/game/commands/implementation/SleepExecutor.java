package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.script.Instruction;
import com.next.system.ThreadAssist;

public class SleepExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameData data) {
        ThreadAssist.sleep(Integer.parseInt(instruction.getArgument()));
    }
}
