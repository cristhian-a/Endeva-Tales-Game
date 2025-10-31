package com.next.core.localization;

import com.next.system.enums.Language;
import org.junit.jupiter.api.Test;

public class LocalizationTest {

    @Test
    void test() {
        String key = "species.human.name";

        Localization l = new Localization(Language.PORTUGUESE);
        String humanName = l.get(key);

        assert humanName.equals("Humano");

        Localization l2 = new Localization(Language.ENGLISH);
        humanName = l2.get(key);

        assert humanName.equals("Human");
    }
}
