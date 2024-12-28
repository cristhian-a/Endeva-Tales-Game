package com.next.graphics;

import com.next.exception.ExceptionHandler;
import com.next.system.Settings;

import java.util.Random;

public class TextPrinter {

    private static int getSlowSpeed() {
        return Settings.getSettings().getSlowTextSpeed();
    }

    private static int getStandardSpeed() {
        return Settings.getSettings().getMediumTextSpeed();
    }

    private static int getQuickSpeed() {
        return Settings.getSettings().getQuickTextSpeed();
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void print(String text, long delayInMillis) {
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

    public static void clearAndPrint(String text) {
        clearConsole();
        print(text);
    }

    public static void clearAndPrintln(String text) {
        clearConsole();
        println(text);
    }

    public static void clearConsole() {
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

    public static void typeTextSlowly(String text) {
        print(text, getSlowSpeed());
    }

    public static void typeText(String text) {
        print(text, getStandardSpeed());
    }

    public static void typeTextQuickly(String text) {
        print(text, getQuickSpeed());
    }

    public static void clearAndTypeSlowly(String text) {
        clearConsole();
        typeTextSlowly(text);
    }

    public static void clearAndType(String text) {
        clearConsole();
        typeText(text);
    }

    public static void sweepText(String text, long delayInMillis) {
        try {
            // Removing two characters each time
            for (int i = text.length(); i >= 0; i = i - 2) {
                clearConsole();
                System.out.print("\r" + text.substring(0, i)); // Overwrite with progressively shorter text
                Thread.sleep(delayInMillis);
            }
            System.out.print("\r"); // Clear the line completely
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ExceptionHandler.handleError("Text sweeping interrupted.");
        }
    }

    public static void sweepText(String text) {
        sweepText(text, 5);
    }

    public static void clearLine() {
        System.out.print("\033[2K"); // Clear the entire line
        System.out.print("\r");      // Move the cursor to the beginning of the line
    }

}
