package org.example.ex03.http.request;

import org.example.ex03.http.request.message.HttpHeader;
import org.example.ex03.http.request.message.HttpRequestLine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SocketInputStream {
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;

    public SocketInputStream(InputStream inputStream, int i) {
        this.inputStream=inputStream;
        inputStreamReader=new InputStreamReader(this.inputStream);
    }

    private String readLine() throws IOException {
        char c;
        int a;
        StringBuffer line=new StringBuffer();
        while ((a=inputStreamReader.read())>=0&&(c= (char) a)!='\n'){
            line.append(c);
        }
        return line.toString();
    }

    public HttpRequestLine readRequestLine() throws IOException {
        String[] line=readLine().split(" ");
        String method=line[0];
        String url=line[1];
        String httpVersion=line[2];
        return new HttpRequestLine(url,method,httpVersion);
    }

    public HttpHeader readHeader() throws IOException {
        String[] header=readLine().split(":");
        return new HttpHeader(header[0],header[1]);
    }
}
