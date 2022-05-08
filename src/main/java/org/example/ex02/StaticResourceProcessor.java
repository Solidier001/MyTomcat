package org.example.ex02;

import java.io.IOException;

public class StaticResourceProcessor implements Processer {
    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();


            //requested maximum length of the queue of incoming connections.
        }
    }
}
