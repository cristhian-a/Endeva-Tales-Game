package com.next.system;

import com.next.exception.ExceptionHandler;

public class ThreadAssist {

    public static void sleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            ExceptionHandler.handleError("Delay interrupted by: " + e.getMessage());
        }
    }

    public static void sleep() {
        sleep(3500);
    }

    public static void quickSleep() {
        sleep(500);
    }
}
