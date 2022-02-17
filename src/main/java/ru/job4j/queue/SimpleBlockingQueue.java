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
                System.out.println("================ NOW QUEUE IS FULL ================");
                this.wait();
            }
            queue.offer(value);
            this.notify();
            System.out.println(value + " is offered. Now queue size is " + queue.size());
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == 0) {
                System.out.println("================ NOW QUEUE IS EMPTY ================");
                this.wait();
            }
            T taken = queue.poll();
            notify();
            System.out.println("It is time to run " + taken + ". "
                    + "Removing it from queue. Now queue size is " + queue.size());
            return taken;
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}