package org.example.ex03.connector.http;

import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;
import org.apache.catalina.util.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class HttpProcessor {
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine httpRequestLine=new HttpRequestLine();;
    private HttpHeader httpHeader;
    protected StringManager sm =StringManager.getInstance("org.example.ex03.connector.http");

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

        String match="&jsessionid=";
        int semicolon=uri.indexOf(match);
        if (semicolon>=0){
            String rest=uri.substring(semicolon+match.length());
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
            uri=uri.substring(0,semicolon)+rest;
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
        while (true){
            httpHeader=new HttpHeader();
            input.readHeader(httpHeader);
            if (httpHeader.getName().length()==0){
                if (httpHeader.getName().length()==0)
                    break;
                else
                    throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
            }
            String name=httpHeader.getName();
            String value=httpHeader.getValue();
            switch (name){
                case "cookie":
                    Cookie[] cookies= RequestUtil.parseCookieHeader(value);
                    for (Cookie cookie:cookies){
                        if (cookie.getName().equals("jseeeionid")){
                            if (!request.isRequestedSessionIdFromCookie()){
                                request.setRequestedSessionId(cookie.getValue());
                                request.setRequestedSessionIdFromCookie(true);
                                request.setRequestedSessionIdFromURL(false);
                            }
                        }
                        request.addCookie(cookie);
                    }
                    break;
                case "content-length":
                    int n=-1;
                    try {
                        n=Integer.parseInt(value);
                    }catch (Exception e){
                        throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
                    }
                    request.setContentLength(n);
                    break;
                case "content-type":
                    request.setContentType(value);
                    break;
                default:
                    break;
            }
            httpHeaders.add(httpHeader);
        }
    }

    private String normalize(String uri) {
        return uri.replace('\\','/');
    }
}
