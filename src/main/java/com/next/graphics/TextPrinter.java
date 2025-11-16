package com.next.graphics;

public interface TextPrinter {
    void print(String text);
    void println();
    void println(String text);
    void print(String text, long delayInMillis);
    void clear();
    void type(String text);
    void typeSlowly(String text);
    void typeQuickly(String text);
    void clearLine();
    void sweepText(String text, long delayInMillis);

    default void sweepText(String text) {
        sweepText(text, 5);
    }
}
