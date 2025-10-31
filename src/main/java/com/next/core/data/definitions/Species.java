package com.next.core.data.definitions;

import com.next.core.data.registry.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species implements Identifiable {
    private String id;
    private String name;
    private String description;
}
