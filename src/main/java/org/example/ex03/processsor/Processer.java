package org.example.ex03.processsor;

import org.example.ex02.Request;
import org.example.ex02.Response;
import org.example.ex03.http.request.HttpRequest;
import org.example.ex03.http.response.HttpResponse;

public interface Processer {
    public void process(HttpRequest request, HttpResponse response);
}
