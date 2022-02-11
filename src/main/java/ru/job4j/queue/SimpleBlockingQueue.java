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

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == this.limit) {
                this.wait();
            }
            queue.offer(value);
            this.notify();
            System.out.println("New size " + queue.size() + " produced " + value + ". ");
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == 0) {
                this.wait();
            }
            T taken = queue.poll();
            notify();
            System.out.println("Size after " + queue.size() + " consumed " + taken + ". ");
            return taken;
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}