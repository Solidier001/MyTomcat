package org.example.ex03.http.request.message;

public class HttpRequestLine {
    private String url;
    private String method;
    private String httpVersion;

    public HttpRequestLine(String url, String method, String httpVersion) {
        this.url = url;
        this.method = method;
        this.httpVersion = httpVersion;
    }

    public HttpRequestLine() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
