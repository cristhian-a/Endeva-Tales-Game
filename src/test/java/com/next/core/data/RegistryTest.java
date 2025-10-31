package com.next.core.data;

import com.next.core.character.CharacterClass;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RegistryTest {

    @Test
    void test() {
        String id = "mage";
        CharacterClass mage = new CharacterClass(id);
        mage.setName("Mage");

        Registry<CharacterClass> CLASSES = new Registry<>();
        CLASSES.register(mage);

        CharacterClass m2 = CLASSES.get(id);
        assert m2.getId().equals(id);
        assert m2.getName().equals("Mage");
    }

    @Test
    void testLoad() {
        String idF = "fighter";
        String idP = "priest";

        CharacterClass fighter = new CharacterClass(idF);
        fighter.setName("Fighter");
        CharacterClass priest = new CharacterClass(idP);
        priest.setName("Priest");

        Registry<CharacterClass> CLASSES = new Registry<>();
        CLASSES.load(List.of(fighter, priest));

        assert CLASSES.get(idF).getName().equals("Fighter");
        assert CLASSES.get(idP).getName().equals("Priest");
    }
}
