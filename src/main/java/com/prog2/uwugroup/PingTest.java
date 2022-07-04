package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.StringPacket;

import java.net.InetAddress;

public class PingTest {
    private Client client;
    public PingTest(InetAddress host, int port) {
        client = new Client(host.getHostAddress(), port);
        client.connect();
        StringPacket sw = new StringPacket();
        client.sendObject(sw);

    }
}
