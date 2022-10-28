package org.example.ex03.processsor.http;

import org.example.ex03.connector.http.HttpConnector;
import org.example.ex03.http.request.HttpRequest;
import org.example.ex03.http.response.HttpResponse;
import org.example.ex03.http.request.SocketInputStream;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
    }

    public void process(Socket socket) {
        SocketInputStream input=null;
        OutputStream output=null;
        try {
            input=new SocketInputStream(socket.getInputStream(),2048);
            output=socket.getOutputStream();
            request=new HttpRequest(input);
            response=new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server","Pyrmont Servlet Container");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRequest(SocketInputStream input,OutputStream output) throws IOException, ServletException {

    }
}
