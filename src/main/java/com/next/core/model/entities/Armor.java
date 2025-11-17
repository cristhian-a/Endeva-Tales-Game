package com.next.core.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Armor extends Item {
    private int resistance;
}
