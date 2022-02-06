package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("1.jpg")) {
            long fileSize = new URL(url).openConnection().getContentLength();
            System.out.println(fileSize);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long sec = 1024 / speed;
            int currentSize = 0;
            long startTime = System.currentTimeMillis();
            System.out.print("\rDownloaded " + currentSize + "from " + fileSize);
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long endTime = System.currentTimeMillis();
                if ((endTime - startTime) * 1000 < sec) {
                    try {
                        Thread.sleep(sec * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentSize += bytesRead;
                System.out.print("\rDownloaded " + currentSize + " from " + fileSize);
                startTime = System.currentTimeMillis();
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}