package me.sutong.java.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Tong on 16/11/4.
 */
public class LogWritter {
    private Path path;

    LogWritter(String path){
        this.path = Paths.get(path);
    }

    LogWritter(Path path){
        this.path = path;
    }

    public void write(String s){
        String out = String.format("%s||||%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()), s);

        try {
            Files.write(this.path, Collections.singletonList(out), StandardOpenOption.APPEND);
        } catch (IOException ignored) {
        }
    }

    public void writeLine(String s){
        String out = String.format("%s||||%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()), s);

        try {
            Files.write(this.path, Collections.singletonList(out), StandardOpenOption.APPEND);
        } catch (IOException ignored) {
        }

    }
}
