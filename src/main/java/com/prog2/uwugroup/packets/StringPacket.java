package com.prog2.uwugroup.packets;

import javafx.event.ActionEvent;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.net.InetAddress;

public class StringPacket implements Serializable {

    private static final long serialVersionUID = 0l;
    private InetAddress sender;
    private int id;
    private ActionEvent event;
    public InetAddress getAdresse(){
        return sender;
    }
    public StringPacket(ActionEvent event, InetAddress sender) {
        this.sender = sender;
        this.event = event;
    }
    public ActionEvent getEvent(){
        return event;
    }
}
