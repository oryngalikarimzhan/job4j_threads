package ru.job4j.cas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void when1IncrementThenGet1() {
        CASCount casCount = new CASCount();
        casCount.increment();
        assertThat(casCount.get(), is(1));
    }

    @Test
    public void when2IncrementThenGet2() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        assertThat(casCount.get(), is(2));
    }

    @Test
    public void whenIncrementThenGet2() throws InterruptedException {
        CASCount casCount = new CASCount();
        casCount.increment();
        Thread thread = new Thread(() -> {
            casCount.increment();
        });
        thread.start();
        thread.join();
        assertThat(casCount.get(), is(2));
    }
}