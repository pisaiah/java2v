package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReadFile {

    public static void main(String[] args) throws IOException {
        File file = new File("data.txt");
        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            System.out.println("Line: " + line);
        }
    }

}