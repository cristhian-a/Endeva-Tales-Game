package com.next.game.commands;

import java.util.Map;

public interface CommandExecutor {
    void execute(String arguments, Map<String, Object> context);
}
