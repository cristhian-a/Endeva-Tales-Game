package com.next.graphics;

public class TextPrinter {

    public static void printText(String text) {
        System.out.println(text);
    }

    public static void clearAndPrint(String text) {
        clearConsole();
        printText(text);
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

}
