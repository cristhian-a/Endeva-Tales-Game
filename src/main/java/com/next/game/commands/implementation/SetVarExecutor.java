package com.next.game.commands.implementation;

import com.next.core.data.GameData;
import com.next.game.commands.CommandExecutor;
import com.next.script.Instruction;

public class SetVarExecutor implements CommandExecutor {

    @Override
    public void execute(Instruction instruction, GameData data) {
        String[] arguments = instruction.getArgument().split("\n");
        for (String a : arguments) {
            String[] splitArg = a.split(":");
            String var = splitArg[0];
            String val = splitArg[1];
            data.getContextData().put(var, val);
        }
    }
}
