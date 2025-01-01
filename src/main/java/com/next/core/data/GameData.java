package com.next.core.data;

import com.next.core.adventure.AdventureData;
import com.next.core.character.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameData {
    private AdventureData adventureData;
    private List<Player> players;
    private Map<String, Object> contextData;

    public GameData() {
        this.contextData = new HashMap<>();
    }

    public AdventureData getAdventureData() {
        return adventureData;
    }

    public void setAdventureData(AdventureData adventureData) {
        this.adventureData = adventureData;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }

    public void setContextData(Map<String, Object> contextData) {
        this.contextData = contextData;
    }
}
