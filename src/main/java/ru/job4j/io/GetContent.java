package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class GetContent {

    private File file;

    public GetContent(File file) {
        this.file = file;
    }

    private synchronized String get(Predicate<Character> filter) {
            String output = "";
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                int data;
                while ((data = bis.read()) != -1) {
                    if (filter.test((char) data)) {
                        output += (char) data;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
    }

    public synchronized String getStandardContent() {
        return get(character -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        return get(character -> character < 0x80);
    }
}
