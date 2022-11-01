package org.example.ex03.http;

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

    public void readRequestLine(HttpRequestLine httpRequestLine) throws IOException {
        String[] line=readLine().split(" ");
        String method=line[0];
        String uri=line[1];
        String[] protocolVersion=line[2].split("/");
        httpRequestLine.setProtocol(protocolVersion[0]);
        httpRequestLine.setVersion(protocolVersion[1]);
        httpRequestLine.setMethod(method);
        httpRequestLine.setUri(uri);
    }

    public int readHeader(HttpHeader httpHeader) throws IOException {
        String line=readLine();
        if (!line.equals("")){
            String[] header=line.split(":");
            httpHeader.setName(header[0]);
            httpHeader.setValue(header[1]);
            return 1;
        }
        else return 0;
    }

    public void close() throws IOException {
        inputStreamReader.close();
        inputStream.close();
    }
}
