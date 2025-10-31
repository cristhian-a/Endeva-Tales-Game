package com.next.core.model.stats;

import com.next.core.data.definitions.enums.ExpertiseRank;
import com.next.core.data.definitions.enums.ItemClass;
import lombok.Data;

@Data
public class Expertise {
    private final ItemClass id;
    private ExpertiseRank rank;
}
