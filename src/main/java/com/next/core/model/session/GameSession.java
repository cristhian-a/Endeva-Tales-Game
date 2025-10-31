package com.next.core.model.session;

import com.next.core.data.scenes.AdventureData;
import com.next.core.model.entities.Character;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameSession {
    private AdventureData adventureData;
    private List<Character> characters;
    private Map<String, Object> contextData;

    public GameSession() {
        this.contextData = new HashMap<>();
    }
}
