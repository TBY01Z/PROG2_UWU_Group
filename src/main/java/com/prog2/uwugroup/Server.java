package com.prog2.uwugroup;

import javafx.application.Platform;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server extends NetworkConnection{

    private int port;

    public Server(int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return true;    //weil es ein server ist
    }

    @Override
    protected String getIP() {
        return null;      //ist egal, deswegen darf hier null zurückgegeben werden
    }

    @Override
    protected int getPort() {
        return port;
    }

}
