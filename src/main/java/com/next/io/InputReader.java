package com.next.io;

import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        return this.scanner.nextLine();
    }

    public int readInt() {
        int i = this.scanner.nextInt();
        this.scanner.nextLine();
        return i;
    }
}
