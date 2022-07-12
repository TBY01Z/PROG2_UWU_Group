package com.prog2.uwugroup.packets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatMessages {
    private final ObservableList<MessagePacket> chat = FXCollections.emptyObservableList();
    public ChatMessages() {}
    public boolean add(MessagePacket message){
        if(!chat.contains(message)){
            chat.add(message);
            return true;
        }
        return false;
    }
    //
    public ObservableList<MessagePacket> chat() {
        return chat;
    }
}
