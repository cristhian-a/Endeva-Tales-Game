package com.next.io;

import java.io.InputStream;

public class FileReader {

    public static InputStream readFile(String path) {
        return FileReader.class.getClassLoader().getResourceAsStream(path);
    }
}
