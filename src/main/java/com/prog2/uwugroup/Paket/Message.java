package com.prog2.uwugroup.Paket;

import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {
    private final String message;
    private final InetAddress sender;
    private final int id;

    public Message(String message, InetAddress sender, int id) {
        this.message = message;
        this.sender = sender;
        this.id = id;
    }


}
