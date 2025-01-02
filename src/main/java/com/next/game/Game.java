package com.next.game;

import com.next.core.adventure.AdventureData;
import com.next.core.data.GameData;
import com.next.game.script.ScriptExecutor;
import com.next.game.script.implementation.ScriptExecutorImpl;
import com.next.graphics.TextBuilder;
import com.next.graphics.TextPrinter;
import com.next.graphics.menu.MainMenu;
import com.next.io.FileReader;
import com.next.io.InputReader;
import com.next.io.JsonReader;
import com.next.script.Instruction;
import com.next.script.ScriptParser;
import com.next.script.implementation.ScriptParserImpl;
import com.next.system.Settings;
import com.next.system.ThreadAssist;

import java.io.IOException;
import java.util.List;

public class Game {

    private boolean isRunning;
    private GameData gameData;
    private InputReader inputReader;
    private final Settings settings;
    private final ScriptExecutor scriptExecutor;

    public Game() {
        this.gameData = new GameData();
        this.inputReader = new InputReader();
        this.scriptExecutor = new ScriptExecutorImpl();

        this.settings = Settings.getSettings();
    }

    public void start() {
        this.isRunning = true;
        this.settings.setDevMode(true);

        TextPrinter.clearConsole();

        var instructions = readScript("");
        scriptExecutor.executeInstructions(instructions, gameData);

//        resolveMainMenu();
//        run();
    }

    public void stop() {
        isRunning = false;
    }

    private void run() {
        do {
            tick();
        } while (isRunning);
    }

    private void tick() {
        TextPrinter.clearConsole();
        for (int i = 0; i < 3; i++) {
            TextPrinter.clearAndPrint(".");
            ThreadAssist.quickSleep();
            TextPrinter.clearAndPrint("..");
            ThreadAssist.quickSleep();
            TextPrinter.clearAndPrint("...");
            ThreadAssist.quickSleep();
        }

        TextPrinter.clearAndTypeSlowly("Sexta-feira, 2 de Março de 1295...");
        ThreadAssist.sleep();
        TextPrinter.sweepText("Sexta-feira, 2 de Março de 1295...");

        TextPrinter.clearAndType("Pressione ENTER para continuar!\n");
        inputReader.read();

        TextPrinter.clearConsole();
        var scenes = this.gameData.getAdventureData().scenes;
        TextPrinter.typeText(scenes.getFirst().text);

        TextPrinter.typeTextQuickly("\nPressione ENTER para continuar!");
        inputReader.read();
        TextPrinter.clearLine();

        TextPrinter.typeText(scenes.get(1).text);

        TextPrinter.typeTextQuickly("\nPressione ENTER para continuar!");
        inputReader.read();

        stop();
    }

    private void resolveMainMenu() {
        TextPrinter.clearAndPrintln(MainMenu.getTitle());

        TextBuilder tb = new TextBuilder();

        List<AdventureData> adventureTitles = JsonReader.getAdventureTitles();
        List<String> titles = adventureTitles.stream().map(x -> x.title).toList();

        String mainMenu = MainMenu.getMainMenu();
        String options = tb.buildMenuText(mainMenu, titles);
        TextPrinter.typeText(options);

        String selectedOption = inputReader.read();
        int optionValue = 0;
        do {
            if (selectedOption.matches("-?\\d+")) {
                optionValue = Integer.parseInt(selectedOption) - 1;
                if (!(optionValue < 0 || optionValue > adventureTitles.size() - 1))
                    break;
            }

            TextPrinter.clearAndPrintln(MainMenu.getTitle());
            TextPrinter.println("Opção Inválida!\n");
            TextPrinter.println(options);
            selectedOption = inputReader.read();
        } while (isRunning);

        var selected = adventureTitles.get(optionValue);

        var result = JsonReader.getAdventureData(selected.fileName);
        TextPrinter.typeTextSlowly("Opção selecionada: " + result.title);
        ThreadAssist.sleep(2000);

        this.gameData.setAdventureData(result);
        this.gameData.setPlayers(List.of(result.player));
    }

    private List<Instruction> readScript(String path) {
        var inputStream = FileReader.readFile("adventures/scripts/thomas-and-siltar/1");
        ScriptParser scriptParser = new ScriptParserImpl();

        try {
            return scriptParser.parseScript(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
