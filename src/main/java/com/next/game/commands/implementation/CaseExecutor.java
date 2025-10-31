package com.next.game.commands.implementation;

import com.next.core.model.session.GameSession;
import com.next.game.commands.CommandExecutor;
import com.next.game.script.ScriptExecutor;
import com.next.script.Instruction;
import com.next.util.ConditionEvaluator;

public class CaseExecutor implements CommandExecutor {

    private final ConditionEvaluator conditionEvaluator;
    private final ScriptExecutor scriptExecutor;

    public CaseExecutor(ScriptExecutor scriptExecutor) {
        this.conditionEvaluator = new ConditionEvaluator();
        this.scriptExecutor = scriptExecutor;
    }

    @Override
    public void execute(Instruction instruction, GameSession data) {
        boolean result = conditionEvaluator.evaluate(instruction.getArgument(), data.getContextData());
        if (result) {
            scriptExecutor.executeInstructions(instruction.getInstructions(), data);
        }
    }
}
