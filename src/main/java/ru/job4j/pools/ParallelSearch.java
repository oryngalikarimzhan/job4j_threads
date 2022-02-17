package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public ParallelSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return searchIndex();
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> leftParallelSearcher = new ParallelSearch(array, object, from, mid);
        ParallelSearch<T> rightParallelSearcher = new ParallelSearch(array, object, mid + 1, to);
        leftParallelSearcher.fork();
        rightParallelSearcher.fork();
        Integer leftJoin = leftParallelSearcher.join();
        Integer rightJoin = rightParallelSearcher.join();
        return leftJoin > rightJoin ? leftJoin : rightJoin;
    }

    private int searchIndex() {
        int rsl = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(object)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static <T> int search(T[] array, T object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(array, object, 0, array.length - 1));
    }

    public static void main(String[] args) {
        Character[] array = new Character[127];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        char object = '!';
        int result = search(array, object);
        System.out.println(result);
    }
}
