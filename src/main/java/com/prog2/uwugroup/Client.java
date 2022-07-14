package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.RemovePlayerPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.function.Consumer;

public class Client extends NetworkConnection {

    private String ip;
    private int port;

    public Client(String ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;   //false, da das hier kein server ist.
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }
}
