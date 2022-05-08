package org.example.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        System.out.println("Starting...");
        HttpServer1 server = new HttpServer1();
        System.out.println("Started");
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            try(Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
            ) {
                Request request=new Request(input);
                request.parse();
                Response response=new Response(output);
                response.setRequest(request);
//              check if this is a request for a static resource
//              or for a servlet

                if (request.getUri().startsWith("/servlet/")){
                    servletProcessor1 processor=new servletProcessor1();
                    processor.process(request,response);
                }
                else {
                    StaticResourceProcessor processor=new StaticResourceProcessor();
                    processor.process(request,response);
                }

                socket.close();
                shutdown=request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}