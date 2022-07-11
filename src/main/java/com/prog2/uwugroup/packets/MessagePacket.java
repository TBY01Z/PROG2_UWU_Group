package com.prog2.uwugroup.packets;

import java.io.Serializable;
import java.net.InetAddress;

public class MessagePacket implements Serializable {
    private static final long serialVersionUID = 1l;
    private int id;
    private final InetAddress sender;
    private final String message;
    private final String userName;

    public MessagePacket(InetAddress sender, String message, String userName) {
        this.sender = sender;
        this.message = message;
        this.userName = userName;
    }
    //

    //

    public InetAddress sender() {
        return sender;
    }

    public String message() {
        return message;
    }

    public String userName() {
        return userName;
    }
}
