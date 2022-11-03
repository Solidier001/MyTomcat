package org.example.ex03.connector;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class ResponseWriter extends PrintWriter {
    public ResponseWriter(@NotNull Writer out) {
        super(out);
    }

    public ResponseWriter(@NotNull Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public ResponseWriter(@NotNull OutputStream out) {
        super(out);
    }

    public ResponseWriter(@NotNull OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public ResponseWriter(@NotNull String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public ResponseWriter(@NotNull String fileName, @NotNull String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public ResponseWriter(@NotNull File file) throws FileNotFoundException {
        super(file);
    }

    public ResponseWriter(@NotNull File file, @NotNull String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }
}
