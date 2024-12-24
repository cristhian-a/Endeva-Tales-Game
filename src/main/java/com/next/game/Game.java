package com.next.game;

import com.next.graphics.TextBuilder;
import com.next.graphics.TextPrinter;
import com.next.graphics.menu.MainMenu;
import com.next.io.InputReader;
import com.next.io.JsonReader;

import java.io.IOException;

public class Game {

    private boolean isRunning;
    private InputReader inputReader;

    public Game() {
        this.inputReader = new InputReader();
    }

    public void start() {
        this.isRunning = true;

        resolveMainMenu();
    }

    public void stop() {
        isRunning = false;
    }

    public void resolveMainMenu() {
        String mainMenu = MainMenu.getTitle() + MainMenu.getMainMenu();
        String[] tales = MainMenu.getTalesOptions();

        TextBuilder tb = new TextBuilder();

        String options = tb.buildMenuText(mainMenu, tales);
        TextPrinter.printText(options);

        String selectedOption = readInput();
        TextPrinter.clearAndPrint("Opção selecionada: " + tales[Integer.parseInt(selectedOption) - 1]);

        try {
            var result = JsonReader.getAdventureData();
            TextPrinter.printText(result.title);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readInput() {
        return inputReader.read();
    }
}
