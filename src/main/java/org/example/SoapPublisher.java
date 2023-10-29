package org.example;

import jakarta.xml.ws.Endpoint;

public class SoapPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9000/soap", new SoapController());
    }
}
