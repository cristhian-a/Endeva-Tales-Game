package com.next.core.localization;

import com.next.io.JsonReader;
import com.next.system.enums.Language;

import java.io.IOException;
import java.util.Map;

public class Localization {

    // File paths must be in the same order as the languages enum
    private static final String[] PATHS = new String[] { "pt_br.json", "en_us.json" };

    private final Map<String, String> texts;

    public Localization(Language language) {
        String path = "languages/" + PATHS[language.ordinal()];
        String filePath = JsonReader.getRootPath() + path;

        try {
            texts = JsonReader.readMap(filePath, String.class, String.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return texts.get(key);
    }
}
