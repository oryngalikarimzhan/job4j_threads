package ru.job4j.io;

import java.io.*;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public static void main(String[] args) throws InterruptedException {
        ParseFile parseFile = new ParseFile(new File("./README.md"));
        GetContent getContent = new GetContent();
        SaveContent saveContent = new SaveContent();
        Thread thread1 = new Thread(
                () -> System.out.println(getContent.get(parseFile, i -> true))
        );
        thread1.start();
        Thread.sleep(1000);
        saveContent.save("bla", parseFile);
        Thread thread2 = new Thread(
                () -> System.out.println(getContent.get(parseFile, i -> i != 74))
        );
        thread2.start();
        thread1.join();
        thread2.join();
    }
}