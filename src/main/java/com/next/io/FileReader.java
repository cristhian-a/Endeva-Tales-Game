package com.next.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileReader {

    public static InputStream readFile(String path) {
        String absolutePath = JsonReader.getRootPath() + "/" + path;

        try {
            return new FileInputStream(absolutePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
