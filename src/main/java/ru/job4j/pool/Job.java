package ru.job4j.pool;

public class Job implements Runnable {

    private int num;

    public Job(int n) {
        num = n;
    }

    public void run() {
        System.out.println("Job " + num + " is running.");
    }

    @Override
    public String toString() {
        return "Job{"
                + "num=" + num
                + '}';
    }
}

