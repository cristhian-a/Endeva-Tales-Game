package com.next.script;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ScriptParser {
    List<Map.Entry<String, String>> parseScript(InputStream scriptInput) throws IOException;
}
