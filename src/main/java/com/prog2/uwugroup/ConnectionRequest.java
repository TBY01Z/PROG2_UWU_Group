package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.StringPacket;

public class ConnectionRequest {
    private Client client;
    public ConnectionRequest(String host, int port) {
        client = new Client(host, port);
        client.connect();
        StringPacket sw = new StringPacket();
        client.sendObject(sw);

    }
}
