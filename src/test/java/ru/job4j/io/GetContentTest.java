package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GetContentTest {

    @Test
    public void test() throws InterruptedException {
        File parseFile = new File("./README.md");
        GetContent getContent = new GetContent(parseFile);
        SaveContent saveContent = new SaveContent(parseFile);
        Thread thread1 = new Thread(
                () -> getContent.getStandardContent()
        );
        thread1.start();
        Thread.sleep(1000);
        saveContent.save("bla");
        Thread thread2 = new Thread(
                () -> getContent.getContentWithoutUnicode()
        );
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(getContent.getStandardContent(), is("bla"));
    }

}