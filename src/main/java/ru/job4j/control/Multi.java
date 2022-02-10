package ru.job4j.control;

public class Multi {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(2);
        Thread master1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                },
                "Master1"
        );
        Thread master2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.count();
                },
                "Master2"
        );
        Thread slave = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master1.start();
        master2.start();
        slave.start();
    }
}