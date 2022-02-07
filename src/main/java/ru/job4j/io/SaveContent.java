package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveContent {

    public synchronized void save(String content, ParseFile parseFile) {
        try (OutputStream o = new FileOutputStream(parseFile.getFile())) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
