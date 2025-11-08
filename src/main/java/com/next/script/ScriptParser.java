package com.next.script;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ScriptParser {
    List<Instruction> parseScript(InputStream scriptInput) throws IOException;
}
