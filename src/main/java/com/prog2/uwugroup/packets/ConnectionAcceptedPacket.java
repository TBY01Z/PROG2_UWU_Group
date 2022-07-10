package com.prog2.uwugroup.packets;

import java.io.Serializable;

public class ConnectionAcceptedPacket implements Serializable {
    private final static long serialVersionUID = 1l;

    private int id;

    private boolean connectionAccepted = false;

    public ConnectionAcceptedPacket(boolean conAcc){
        this.connectionAccepted = conAcc;
    }

    public boolean isAccepted(){
        return connectionAccepted;
    }

    public void setAccepted(boolean connectionAccepted){
        this.connectionAccepted = connectionAccepted;
    }
}
