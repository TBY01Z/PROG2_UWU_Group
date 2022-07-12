package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.ChatMessages;
import com.prog2.uwugroup.packets.ChatPacket;
import com.prog2.uwugroup.packets.MessagePacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.event.ActionEvent;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionRequest {
    private Client client;
    public ConnectionRequest(String host, int port) {
        client = new Client(host, port);
        client.connect();
        ChatMessages chatMessages = new ChatMessages();
        try {
            MessagePacket message = new MessagePacket(InetAddress.getLocalHost(),"Connect?", "Bob");
            chatMessages.add(message);
            ChatPacket cp = new ChatPacket(message.toString());
            client.sendObject(cp);  //sendObject(chatMessages)
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}
