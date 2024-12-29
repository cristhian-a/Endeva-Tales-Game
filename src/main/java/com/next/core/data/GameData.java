package com.next.core.data;

import com.next.core.adventure.AdventureData;
import com.next.core.character.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameData {
    public AdventureData adventureData;
    public List<Player> players;
    public Map<String, Object> additionalData = new HashMap<>();
}
