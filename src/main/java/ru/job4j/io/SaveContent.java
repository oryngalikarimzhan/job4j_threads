package ru.job4j.io;

import java.io.*;

public class SaveContent {

    private File file;

    public SaveContent(File file) {
        this.file = file;
    }

    public synchronized void save(String content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = content.getBytes();
            bos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
