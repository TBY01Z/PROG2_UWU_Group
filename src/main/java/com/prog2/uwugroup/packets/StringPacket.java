package com.prog2.uwugroup.packets;

import javafx.event.ActionEvent;

import java.io.NotSerializableException;
import java.io.Serializable;

public class StringPacket implements Serializable {

    private static final long serialVersionUID = 0l;
    private int id;
    private ActionEvent event;

    public StringPacket(ActionEvent event) {
        this.event = event;
    }
    public ActionEvent getEvent(){
        return event;
    }
}
