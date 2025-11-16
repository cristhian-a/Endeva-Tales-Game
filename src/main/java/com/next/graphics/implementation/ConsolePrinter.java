package com.next.graphics.implementation;

import com.next.exception.ExceptionHandler;
import com.next.graphics.TextPrinter;
import com.next.system.Settings;

import java.util.Random;

public class ConsolePrinter implements TextPrinter {

    private int getSlowSpeed() {
        return Settings.getSettings().getSlowTextSpeed();
    }

    private int getStandardSpeed() {
        return Settings.getSettings().getMediumTextSpeed();
    }

    private int getQuickSpeed() {
        return Settings.getSettings().getQuickTextSpeed();
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void print(String text, long delayInMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayInMillis + new Random().nextInt(20));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                ExceptionHandler.handleError("Typing effect interrupted.");
            }
        }
    }

    @Override
    public void clear() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    @Override
    public void type(String text) {
        print(text, getStandardSpeed());
    }

    @Override
    public void typeSlowly(String text) {
        print(text, getSlowSpeed());
    }

    @Override
    public void typeQuickly(String text) {
        print(text, getQuickSpeed());
    }

    @Override
    public void sweepText(String text, long delayInMillis) {
        try {
            // Removing two characters each time
            for (int i = text.length(); i >= 0; i = i - 2) {
                clear();
                System.out.print("\r" + text.substring(0, i)); // Overwrite with progressively shorter text
                Thread.sleep(delayInMillis);
            }
            System.out.print("\r"); // Clear the line completely
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ExceptionHandler.handleError("Text sweeping interrupted.");
        }
    }

    @Override
    public void clearLine() {
        System.out.print("\033[2K"); // Clear the entire line
        System.out.print("\r");      // Move the cursor to the beginning of the line
    }

}
