package org.example.ex03.processsor.impl;

import org.example.ex03.connector.http.HttpConnector;
import org.example.ex03.http.request.HttpRequest;
import org.example.ex03.http.request.message.HttpHeader;
import org.example.ex03.http.request.message.HttpRequestLine;
import org.example.ex03.http.response.HttpResponse;
import org.example.ex03.http.SocketInputStream;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class HttpProcessor {
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine httpRequestLine=new HttpRequestLine();;
    private HttpHeader httpHeader;

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
            parseRequest(input,output);
            parseHeaders(input);

            if(request.getRequestURI().startsWith("/servlet/")){
                ServletProcessor processor=new ServletProcessor();
                processor.process(request,response);
            }
            else {
                StaticResourceProcessor processor=new StaticResourceProcessor();
                processor.process(request,response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void parseRequest(SocketInputStream input,OutputStream output) throws IOException, ServletException {
        input.readRequestLine(httpRequestLine);
        String method=httpRequestLine.getMethod();
        String uri=null;
        String uriString=httpRequestLine.getUri();
        String protocol=httpRequestLine.getProtocol();
        int question=uriString.indexOf('?');

        if (question>=0){
            request.setQueryString(uriString.substring(question+1));
            uri=uriString.substring(0,question);
        }
        else {
            request.setQueryString(null);
            uri=uriString;
        }

        if (!uri.startsWith("/")){
            int pos=uri.indexOf("://");
            if (pos!=-1){
                pos=uri.indexOf('/',pos+3);
                if (pos==-1) uri="";
                else uri=uri.substring(pos);
            }
        }

        String query=request.getQueryString();
        String match="&jsessionid=";
        int semicolon=query.indexOf(match);
        if (semicolon>=0){
            String rest=query.substring(semicolon+match.length());
            int semicolon2=rest.indexOf('&');
            if (semicolon2>=0){
                request.setRequestedSessionId(rest.substring(0,semicolon2));
                rest.substring(semicolon2);
            }
            else {
                request.setRequestedSessionId(rest);
                rest="";
            }
            request.setRequestedSessionIdFromURL(true);
            query=query.substring(0,semicolon)+rest;
        }
        else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionIdFromURL(false);
        }

        request.setMethod(method);
        request.setProtocol(protocol);

        String normalizedUri=normalize(uri);
        if (normalizedUri!=null) request.setRequestURI(normalizedUri);
        else throw new ServletException("Invalid URI"+uri+"'");

    }

    private void parseHeaders(SocketInputStream input) throws IOException, ServletException {
        LinkedList<HttpHeader> httpHeaders=new LinkedList();
        HttpHeader httpHeader=new HttpHeader();
        while (input.readHeader(httpHeader)==1){
            httpHeaders.add(httpHeader);
            httpHeader=new HttpHeader();
        }
    }

    private String normalize(String uri) {
        return uri.replace('\\','/');
    }
}
