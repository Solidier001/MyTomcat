package org.example.ex03.connector.http;


import java.io.IOException;

public class StaticResourceProcessor implements Processer {
    public void process(HttpRequest request, HttpResponse response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();


            //requested maximum length of the queue of incoming connections.
        }
    }
}
