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
        String s = this.scanner.nextLine();
        boolean isParseable = s.matches("-?\\d+(\\.\\d+)?");

        if (isParseable)
            return Integer.parseInt(s);

        return -999;
    }
}
