package ru.job4j.future;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        Map<Integer, Sums> map = new HashMap<>();
        for (int k = 0; k < n; k++) {
            map.put(k, count(matrix, k));
        }
        for (Integer key : map.keySet()) {
            sums[key] = map.get(key);
        }
        return sums;
    }

    public static Sums count(int[][] data, int rowOrCol) {
        int rowSum = 0;
        for (int i = 0; i < data.length; i++) {
            rowSum += data[rowOrCol][i];
        }
        int colSum = 0;
        for (int i = 0; i < data.length; i++) {
            colSum += data[i][rowOrCol];
        }
        return new Sums(rowSum, colSum);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int k = 0; k < n; k++) {
            futures.put(k, getTask(matrix, k));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int rowOrCol) {
        return CompletableFuture.supplyAsync(() -> count(data, rowOrCol));
    }


    /*
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * */
    public static void main(String[] args) {
        try {
            Sums[] rsl = RolColSum.asyncSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
            Arrays.stream(rsl).forEach(System.out::println);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Sums[] rsl = RolColSum.sum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Arrays.stream(rsl).forEach(System.out::println);
    }

}