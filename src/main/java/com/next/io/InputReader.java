package com.next.io;

import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return this.scanner.next();
    }
}
