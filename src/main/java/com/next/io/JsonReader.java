package com.next.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.next.core.data.scenes.AdventureData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

public class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getRootPath() {
        try {
            File codeLocation = new File(JsonReader.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());

            // When running as JAR
            if (codeLocation.isFile() && codeLocation.getName().endsWith(".jar")) {
                return codeLocation.getParentFile().getPath();
            }

            // When running from IDE
            return codeLocation.getPath();

        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to determine root path", e);
        }
    }


    public static AdventureData getAdventureData(String fileName) {
        String path = "adventures/" + fileName;
        try (InputStream input = FileReader.readFile(path)) {
            if (input == null) {
                throw new IOException("Resource not found: " + path);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, AdventureData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AdventureData> getAdventureTitles() {
        List<AdventureData> titles = new ArrayList<>();

        try {
            String path = getRootPath();
            var dir = new java.io.File(path + "/adventures");

            if (dir.isDirectory()) {
                for (var file : Objects.requireNonNull(dir.listFiles())) {
                    if (file.isFile()) {
                        try (InputStream input = new FileInputStream(path + "/adventures/" + file.getName())) {
                            JsonNode rootNode = mapper.readTree(input);
                            AdventureData data = new AdventureData();
                            data.fileName = file.getName();
                            data.title = rootNode.path("title").asText();
                            data.description = rootNode.path("description").asText();

                            titles.add(data);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return titles;
    }

    public static <T> T readObject(String filePath, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(filePath), clazz);
    }

    public static <T, U> Map<T, U> readMap(String filePath, Class<T> key, Class<U> value) throws IOException {
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructMapType(HashMap.class, key, value));
    }

    public static <T> List<T> readList(String filePath, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
