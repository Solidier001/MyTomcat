package org.example;
import org.example.ex02.Request;

//import javax.servlet.*;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws UnknownHostException {
        System.out.println(InetAddress.getByName("localhost"));
    }

}
