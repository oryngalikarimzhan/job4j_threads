package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddModelThen() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        assertThat(cache.get(1), is(model));
    }

    @Test
    public void whenAddAndDeleteThenNull() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        cache.delete(model);
        assertNull(cache.get(1));
    }

    @Test
    public void whenUpdateModelThenChangeVersion() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        cache.update(model);
        assertThat(cache.get(1).getVersion(), is(1));
    }

    @Test
    public void whenUpdateModelNameThenThisName() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        model.setName("second");
        cache.update(model);
        assertThat(cache.get(1).getName(), is("second"));
    }

    @Test(expected = OptimisticException.class)
    public void whenCanNotUpdate() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        Base model1 = new Base(1, 1);
        cache.add(model);
        cache.update(model1);
    }
}