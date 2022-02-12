package ru.job4j.pool;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i != this.size; i++) {
            threads.add(i, new PoolJob());
            threads.get(i).start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        synchronized (tasks) {
            tasks.offer(job);
        }
    }

    public void shutdown() {
        threads.forEach(i -> i.interrupt());
        System.out.println("all threads are shutted down");
    }

    private class PoolJob extends Thread {

        public void run() {

            Runnable job = null;

            while (!Thread.currentThread().isInterrupted()) {
                synchronized (tasks) {
                    try {
                        job = tasks.poll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    job.run();
                }
            }
        }
    }
}


