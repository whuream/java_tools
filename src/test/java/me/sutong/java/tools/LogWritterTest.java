package me.sutong.java.tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tong on 16/11/4.
 */
public class LogWritterTest {
    LogWritter logWritter = new LogWritter("D:\\Download\\a.out");

    @Test
    public void write() throws Exception {
        logWritter.write("test");
    }

    @Test
    public void writeLine() throws Exception {

    }

}