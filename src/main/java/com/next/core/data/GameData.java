package com.next.core.data;

import com.next.core.adventure.AdventureData;
import com.next.core.character.Character;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameData {
    private AdventureData adventureData;
    private List<Character> characters;
    private Map<String, Object> contextData;

    public GameData() {
        this.contextData = new HashMap<>();
    }
}
