package ru.job4j.pool;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();

        for (int i = 0; i < 5000; i++) {
            Job task = new Job(i);
            pool.work(task);
        }

        pool.shutdown();
    }
}