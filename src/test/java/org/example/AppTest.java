package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private InputStreamReader inputStreamReader;

    @Test
    public void shouldAnswerWithTrue() throws IOException {
        System.out.println(new StringBuffer().toString().split(":"));
    }

    private String readLine() throws IOException {
        char c;
        int a;
        StringBuffer line=new StringBuffer();
        while ((a=inputStreamReader.read())>=0&&(c= (char) a)!='\n'){
            line.append(c);
        }
        return line.toString();
    }
}
