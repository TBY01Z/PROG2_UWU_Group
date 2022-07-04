package com.prog2.uwugroup;

public class pingTest {
    private Client client;
    public pingTest(String host, int port) {
        client = new Client(host, port);
        client.connect();
        //client.sendObject();
    }
}
