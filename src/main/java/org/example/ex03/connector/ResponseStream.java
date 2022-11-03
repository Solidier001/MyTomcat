package org.example.ex03.connector;

import org.example.ex03.connector.http.HttpResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseStream extends OutputStream {
    private HttpResponse httpResponse;
    private boolean commit;

    public ResponseStream() {
    }

    public ResponseStream(HttpResponse httpResponse) {
        this.httpResponse=httpResponse;
    }

    @Override
    public void write(int b) throws IOException {
        httpResponse.getOutputStream().write(b);
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public boolean isCommit() {
        return commit;
    }
}
