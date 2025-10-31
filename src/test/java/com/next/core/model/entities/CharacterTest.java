package com.next.core.model.entities;

import com.next.core.data.definitions.enums.ItemClass;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    @Test
    void test() {
        Character c = new Character();
        assert c.getExpertises().length == ItemClass.values().length;
    }
}
