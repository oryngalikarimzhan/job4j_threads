package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.pools.ParallelSearch.search;

public class ParallelSearchTest {
    @Test
    public void test1() {
        Character[] array = new Character[127];
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) i;
        }
        char object = '!';
        int result = search(array, object);
        assertThat(result, is((int) object));
    }
}