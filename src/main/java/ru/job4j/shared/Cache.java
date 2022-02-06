package ru.job4j.shared;

public final class Cache {
    private static Cache cache;

    public synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
            System.out.println("if not created yet - " + cache);
        } else {
            System.out.println("if created already - " + cache);
        }
        return cache;
    }

    public synchronized Cache get() {
        return cache;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        Thread first = new Thread(cache::instOf);
        Thread second = new Thread(cache::instOf);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println("getting - " + cache.get());
    }
}