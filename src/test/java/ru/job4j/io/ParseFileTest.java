package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParseFileTest {

    @Test
    public void test() throws InterruptedException {
        ParseFile parseFile = new ParseFile(new File("./README.md"));
        GetContent getContent = new GetContent(parseFile.getFile());
        SaveContent saveContent = new SaveContent(parseFile.getFile());
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