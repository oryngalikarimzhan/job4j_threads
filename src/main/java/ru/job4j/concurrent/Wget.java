package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    System.out.println("Thread started");
                    for (int index = 0; index != 101; index++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print("\rLoading : " + index  + "%");
                    }
                    System.out.println();
                    System.out.println("Loaded");
                }
        );
        thread.start();
    }
}

