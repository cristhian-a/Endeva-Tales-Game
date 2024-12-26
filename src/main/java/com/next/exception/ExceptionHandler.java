package com.next.exception;

import com.next.game.Settings;

public class ExceptionHandler {

    private static boolean isDevMode() {
        return Settings.getSettings().isDevMode();
    }

    public static void handleException(Exception e) {
        if (isDevMode())
            e.printStackTrace();
    }

    public static void handleError(String message) {
        if (isDevMode())
            System.err.println(message);
    }

    public static void handleUncaughtException(Thread thread, Throwable throwable) {
        if (throwable instanceof Exception) {
            handleException((Exception) throwable);
        } else {
            System.err.println("Unhandled error: " + throwable.getMessage());
            throwable.printStackTrace();
        }
    }

    public static void initialize() {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::handleUncaughtException);
    }
}
