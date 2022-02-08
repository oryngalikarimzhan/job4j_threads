package ru.job4j.io;

import java.io.*;

public class SaveContent {

    private File file;

    public SaveContent(File file) {
        this.file = file;
    }

    public synchronized void save(String content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                bos.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
