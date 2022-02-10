package ru.job4j.queue;

import java.util.Random;

public class QueueJob {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(5);
        Thread producer = new Thread(
                () -> {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " working");
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queue.offer(new Random().nextInt(100));
                    }
                }, "Producer");
        producer.start();
        Thread.sleep(10000);
        Thread consumer = new Thread(
                () -> {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " working");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queue.poll();
                    }
                }, "Consumer");
        consumer.start();
    }
}
