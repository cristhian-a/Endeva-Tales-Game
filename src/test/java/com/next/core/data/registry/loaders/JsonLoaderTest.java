package com.next.core.data.registry.loaders;

import com.next.core.data.registry.GameData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonLoaderTest {

    @Test
    void testClasses() throws IOException {
        JsonLoader loader = new JsonLoader("data");
        loader.loadAll();

        assert GameData.CLASSES.get("fighter") != null;
    }

    @Test
    void testSpecies() throws IOException {
        JsonLoader loader = new JsonLoader("data");
        loader.loadAll();

        assert GameData.SPECIES.get("human") != null;
    }
}
