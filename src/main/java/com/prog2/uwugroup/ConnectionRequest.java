package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.StringPacket;
import javafx.event.ActionEvent;

public class ConnectionRequest {
    private Client client;
    public ConnectionRequest(String host, int port, ActionEvent event) {
        client = new Client(host, port);
        client.connect();
        StringPacket sw = new StringPacket(event);
        client.sendObject(sw);
    }
}
