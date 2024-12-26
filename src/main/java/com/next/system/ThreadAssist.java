package com.next.system;

import com.next.exception.ExceptionHandler;

public class ThreadAssist {

    public static void delay(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            ExceptionHandler.handleError("Delay interrupted by: " + e.getMessage());
        }
    }

    public static void delay() {
        delay(3500);
    }

    public static void quickDelay() {
        delay(500);
    }
}
