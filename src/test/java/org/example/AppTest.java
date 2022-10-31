package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue(){
        String uri="https://github.com/search?q=ssh+netty&type=Repositories";
        int pos=uri.indexOf("://");
        System.out.println(uri.charAt(pos));
        System.out.println(uri.charAt(pos+3));
        pos=uri.indexOf('/',pos+3);
        System.out.println(uri.charAt(pos));
        uri=uri.substring(pos);
        System.out.println(uri);
    }
}
