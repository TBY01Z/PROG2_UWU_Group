package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.StringPacket;

public class PingTest {
    private Client client;
    public PingTest(String host, int port) {
        client = new Client(host, port);

        client.connect();client.run();
        System.out.println(0);
        StringPacket sw = new StringPacket();
        client.sendObject(sw);
        System.out.println(1);
    }
}
