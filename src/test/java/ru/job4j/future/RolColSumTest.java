package ru.job4j.future;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RolColSumTest {
    @Test
    public void test1() throws ExecutionException, InterruptedException {

        RolColSum.Sums[] rsl = RolColSum.asyncSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)};
        assertThat(rsl, is(expected));
    }

    @Test
    public void test2() {
        RolColSum.Sums[] rsl = RolColSum.sum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)};
        assertThat(rsl, is(expected));
    }
}