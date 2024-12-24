package com.next.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.next.core.adventure.AdventureData;

import java.io.IOException;
import java.io.InputStream;

public class JsonReader {

    public static AdventureData getAdventureData() throws IOException {
        try (InputStream input = JsonReader.class.getClassLoader().getResourceAsStream("adventures/lost-cavern.json")) {
            if (input == null) {
                throw new IOException("Resource not found: adventures/lost-cavern.json");
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, AdventureData.class);
        }
    }
}
