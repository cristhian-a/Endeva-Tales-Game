package com.next.system;

import com.next.system.enums.Language;
import lombok.Data;

@Data
public class Settings {

    private static Settings settings;

    private boolean isDevMode;
    private Language language;
    private int textSpeed;

    private int slowTextSpeed;
    private int mediumTextSpeed;
    private int quickTextSpeed;

    private Settings() {
        this.isDevMode = false;
        this.language = Language.PORTUGUESE;

        this.textSpeed = 1;
        this.slowTextSpeed = this.textSpeed * 50;
        this.mediumTextSpeed = this.textSpeed * 25;
        this.quickTextSpeed = this.textSpeed * 10;
    }

    public static Settings getSettings() {
        if (settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    public boolean isDevMode() {
        return isDevMode;
    }

    public void setDevMode(boolean devMode) {
        isDevMode = devMode;
    }

    public static void setSettings(Settings settings) {
        Settings.settings = settings;
    }

    public void setTextSpeed(int textSpeed) {
        this.textSpeed = textSpeed;

        this.slowTextSpeed = textSpeed * 50;
        this.mediumTextSpeed = textSpeed * 25;
        this.quickTextSpeed = textSpeed * 10;
    }
}
