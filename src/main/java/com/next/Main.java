package com.next;

import com.next.exception.ExceptionHandler;
import com.next.game.Game;

public class Main {

    public static void main(String[] args) {
        ExceptionHandler.initialize();

        new Game().start();
    }
}