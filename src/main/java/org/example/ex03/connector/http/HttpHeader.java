package org.example.ex03.connector.http;

public class HttpHeader {
    private String name;
    private String value;
    private int nameEnd;
    private int valueEnd;

    public HttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public HttpHeader() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNameEnd(int nameEnd) {
        this.nameEnd = nameEnd;
    }

    public void setValueEnd(int valueEnd) {
        this.valueEnd = valueEnd;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getNameEnd() {
        return nameEnd;
    }

    public int getValueEnd() {
        return valueEnd;
    }
}
