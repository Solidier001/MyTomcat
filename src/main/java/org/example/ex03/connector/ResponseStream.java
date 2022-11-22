package org.example.ex03.connector;

import org.example.ex03.connector.http.HttpResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseStream extends ServletOutputStream {
    private HttpResponse httpResponse;
    private boolean commit;
    private boolean ready=false;


    public ResponseStream() {
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    public ResponseStream(HttpResponse httpResponse) {
        this.httpResponse=httpResponse;
    }

    @Override
    public void write(int b) throws IOException {
        OutputStream outputStream=httpResponse.getOutput();
        outputStream.write(b);
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public boolean isCommit() {
        return commit;
    }
}
