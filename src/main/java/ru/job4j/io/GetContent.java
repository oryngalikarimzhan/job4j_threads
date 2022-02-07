package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContent {

    public synchronized String get(ParseFile parseFile, Predicate<Character> filter) {
            String output = "";
            try (InputStream i = new FileInputStream(parseFile.getFile())) {
                int data;
                while ((data = i.read()) > 0) {
                    if (filter.test((char) data)) {
                        output += (char) data;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        }
}
