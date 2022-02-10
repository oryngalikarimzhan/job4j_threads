package ru.job4j.queue;

import net.jcip.annotations.*;
import java.util.*;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) {
        synchronized (this) {
            while (this.queue.size() == this.limit) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (queue.isEmpty()) {
                this.notify();
            }
            queue.add(value);
            System.out.print("Size " + queue.size() + " ");
        }
    }

    public T poll() {
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (this.queue.size() == this.limit) {
                this.notify();
            }
            System.out.print("Size " + queue.size() + " ");
            return queue.poll();
        }
    }
}