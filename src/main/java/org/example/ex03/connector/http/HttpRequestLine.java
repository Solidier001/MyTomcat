package org.example.ex03.connector.http;

public class HttpRequestLine {
    private String uri;
    private String method;
    private String protocol;
    private String version;

    public HttpRequestLine(String uri, String method, String protocol, String version) {
        this.uri = uri;
        this.method = method;
        this.protocol = protocol;
        this.version = version;
    }

    public HttpRequestLine() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
