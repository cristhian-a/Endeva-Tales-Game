package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.script.Instruction;
import com.next.system.ThreadAssist;

public class SleepExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameSession data) {
        ThreadAssist.sleep(Integer.parseInt(instruction.getArgument()));
    }
}
