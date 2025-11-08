package com.next.script;

import com.next.io.InputReader;
import lombok.Data;

@Data
public class ExecutorDependencies {
    private InputReader inputReader;
    private ScriptParser scriptParser;
}
