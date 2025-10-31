package com.next.core.character;

import com.next.core.character.enums.ExpertiseRank;
import com.next.core.character.enums.ItemClass;
import lombok.Data;

@Data
public class Expertise {
    private final ItemClass id;
    private ExpertiseRank rank;
}
