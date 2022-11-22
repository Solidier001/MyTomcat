package org.example.ex03.connector.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SocketInputStream {
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;

    public SocketInputStream(InputStream inputStream, int i) {
        this.inputStream = inputStream;
        inputStreamReader = new InputStreamReader(this.inputStream);
    }

    private String readLine() throws IOException {
        char c;
        int a;
        StringBuffer line = new StringBuffer();
        while ((a = inputStreamReader.read()) >= 0 && (c = (char) a) != '\n') {
            line.append(c);
        }
        return line.toString();
    }

    public void readRequestLine(HttpRequestLine httpRequestLine) throws IOException {
        String lineString = readLine();
        try {
            String[] line = lineString.split(" ");
            String method = line[0];
            String uri = line[1];
            String[] protocolVersion = line[2].split("/");
            httpRequestLine.setProtocol(protocolVersion[0]);
            httpRequestLine.setVersion(protocolVersion[1]);
            httpRequestLine.setMethod(method);
            httpRequestLine.setUri(uri);
        }catch (RuntimeException e){
            if (lineString==null||lineString.equals(""))return;
            else e.printStackTrace();
        }
    }

    public void readHeader(HttpHeader httpHeader) throws IOException {
        String line = readLine();
        if (line.contains(":")) {
            int separator = line.indexOf(':');
            String name = line.substring(0, separator);
            String value = line.substring(separator + 1);
            httpHeader.setName(name.trim());
            httpHeader.setValue(value.trim());
        } else {
            httpHeader.setName("");
            httpHeader.setValue("");
        }

    }

    public void close() throws IOException {
        inputStreamReader.close();
        inputStream.close();
    }
}
