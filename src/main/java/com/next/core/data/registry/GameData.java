package com.next.core.data.registry;

public class GameData {

    private static GameData instance;

    private GameData() {}

    public GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }
}
