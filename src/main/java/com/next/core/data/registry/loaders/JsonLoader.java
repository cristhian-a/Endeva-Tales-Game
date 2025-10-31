package com.next.core.data.registry.loaders;

import com.next.core.data.definitions.CharacterClass;
import com.next.core.data.definitions.Species;
import com.next.core.data.dto.CharacterClassDTO;
import com.next.core.data.dto.SpeciesDTO;
import com.next.core.data.registry.GameData;
import com.next.io.JsonReader;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class JsonLoader {

    private final File root;

    public JsonLoader(String path) {
        String root = JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        this.root = new File(root + "/" + path);
    }

    public void loadAll() throws IOException {
        loadFiles(new File(root, "classes"), this::registerClass);
        loadFiles(new File(root, "species"), this::registerSpecies);
    }

    private void loadFiles(File folder, Consumer<File> registerMethod) {
        File[] files = folder.listFiles((x, name) -> name.endsWith(".json"));
        if (files == null) return;

        for (File file : files) registerMethod.accept(file);
    }

    private void registerClass(File file) {
        CharacterClassDTO dto = null;
        try {
            dto = JsonReader.readObject(file.getPath(), CharacterClassDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var builder = CharacterClass.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription());
        GameData.CLASSES.register(builder.build());
    }

    private void registerSpecies(File file) {
        SpeciesDTO dto = null;
        try {
            dto = JsonReader.readObject(file.getPath(), SpeciesDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var builder = Species.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription());
        GameData.SPECIES.register(builder.build());
    }
}
