package com.next.io.implementation;

import com.next.io.InputReader;

import java.util.Scanner;

public class SystemInReader implements InputReader {

    private final Scanner scanner;

    public SystemInReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return this.scanner.nextLine();
    }

    @Override
    public int readInt() {
        String s = this.scanner.nextLine();
        boolean isParseable = s.matches("-?\\d+(\\.\\d+)?");

        if (isParseable)
            return Integer.parseInt(s);

        return -999;
    }
}
