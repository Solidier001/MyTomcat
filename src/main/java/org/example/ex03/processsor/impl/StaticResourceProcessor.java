package org.example.ex03.processsor.impl;


import org.example.ex02.Request;
import org.example.ex02.Response;
import org.example.ex03.http.request.HttpRequest;
import org.example.ex03.http.response.HttpResponse;
import org.example.ex03.processsor.Processer;

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
