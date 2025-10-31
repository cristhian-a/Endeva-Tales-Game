package com.next.core.character;

import com.next.core.character.enums.ItemClass;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    @Test
    void test() {
        Character c = new Character();
        assert c.getExpertises().length == ItemClass.values().length;
    }
}
