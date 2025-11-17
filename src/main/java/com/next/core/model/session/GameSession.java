package com.next.core.model.session;

import com.next.core.data.scenes.AdventureData;
import com.next.core.model.entities.Character;
import com.next.util.PCG32;
import com.next.util.Rng;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameSession {
    private AdventureData adventureData;
    private List<Character> characters;
    private Rng rng;
    private Map<String, Object> contextData;

    public GameSession() {
        this.contextData = new HashMap<>();
        this.rng = new PCG32(1234567890, 1);
    }

    public GameSession(Rng rng) {
        this.contextData = new HashMap<>();
        this.rng = rng;
    }
}
