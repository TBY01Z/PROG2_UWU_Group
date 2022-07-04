package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.StringPacket;

public class PingTest {
    private Client client;
    public PingTest(String host, int port) {
        client = new Client(host, port);
        client.connect();
        StringPacket sw = null;
        client.sendObject(sw);
    }
}
