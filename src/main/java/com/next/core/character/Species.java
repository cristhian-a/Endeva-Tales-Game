package com.next.core.character;

import com.next.core.data.Identifiable;
import lombok.Data;

@Data
public class Species implements Identifiable {
    private String id;
    private String name;
}
