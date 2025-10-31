package com.next.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.next.core.data.scenes.AdventureData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getRootPath() {
        return JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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
            // Get the path to the JAR or file location
            String path = JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            var decodedPath = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8);

            if (decodedPath.endsWith(".jar")) {
                // Handle resources inside a JAR
                try (JarFile jarFile = new JarFile(decodedPath)) {
                    var entries = jarFile.entries();

                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String entryName = entry.getName();

                        if (entryName.startsWith("adventures/") && entryName.endsWith(".json")) {
                            try (InputStream input = JsonReader.class.getClassLoader().getResourceAsStream(entryName)) {
                                if (input != null) {
                                    JsonNode rootNode = mapper.readTree(input);
                                    AdventureData data = new AdventureData();
                                    data.fileName = entryName.substring(entryName.lastIndexOf('/') + 1);
                                    data.title = rootNode.path("title").asText();
                                    data.description = rootNode.path("description").asText();

                                    titles.add(data);
                                }
                            }
                        }
                    }
                }
            } else {
                // Handle resources in development environment (outside JAR)
                ClassLoader classLoader = JsonReader.class.getClassLoader();
                var dir = new java.io.File(decodedPath + "adventures");

                if (dir.isDirectory()) {
                    for (var file : Objects.requireNonNull(dir.listFiles())) {
                        try (InputStream input = classLoader.getResourceAsStream("adventures/" + file.getName())) {
                            if (input != null) {
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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return titles;
    }

    public static <T> List<T> loadResources(String directory, String extension, Class<T> clazz) {
        List<T> resources = new ArrayList<>();

        try {
            String path = JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            var decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);

            if (decodedPath.endsWith(".jar")) {
                // Handle resources inside a JAR
                try (JarFile jarFile = new JarFile(decodedPath)) {
                    var entries = jarFile.entries();

                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String entryName = entry.getName();

                        if (entryName.startsWith(directory + "/") && entryName.endsWith(extension)) {
                            try (InputStream input = JsonReader.class.getClassLoader().getResourceAsStream(entryName)) {
                                if (input != null) {
                                    T resource = mapper.readValue(input, clazz);
                                    resources.add(resource);
                                }
                            }
                        }
                    }
                }
            } else {
                // Handle resources in development environment (outside JAR)
                ClassLoader classLoader = JsonReader.class.getClassLoader();
                var dir = new java.io.File(decodedPath + directory);

                if (dir.isDirectory()) {
                    for (var file : Objects.requireNonNull(dir.listFiles((d, name) -> name.endsWith(extension)))) {
                        try (InputStream input = classLoader.getResourceAsStream(directory + "/" + file.getName())) {
                            if (input != null) {
                                T resource = mapper.readValue(input, clazz);
                                resources.add(resource);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load resources from " + directory, e);
        }

        return resources;
    }

    public static List<AdventureData> loadAllAdventures() {
        return loadResources("adventures", ".json", AdventureData.class);
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
