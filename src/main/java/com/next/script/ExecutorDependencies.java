package com.next.script;

import com.next.graphics.TextPrinter;
import com.next.io.InputReader;
import lombok.Data;

@Data
public class ExecutorDependencies {
    private InputReader inputReader;
    private TextPrinter textPrinter;
    private ScriptParser scriptParser;
}
