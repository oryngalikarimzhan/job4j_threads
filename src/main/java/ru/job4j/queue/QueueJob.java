package ru.job4j.queue;

import java.util.Random;

public class QueueJob {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(5);
        Thread producer = new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(1500);
                            queue.offer(new Random().nextInt(100));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Producer");
        producer.start();
        Thread.sleep(10000);
        Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            queue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "Consumer");
        consumer.start();
    }
}
