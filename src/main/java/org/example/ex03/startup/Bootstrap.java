package org.example.ex03.startup;

import org.example.ex03.connector.http.HttpConnector;

public final class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector=new HttpConnector();
        connector.start();
    }
}