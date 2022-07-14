package com.prog2.uwugroup.packets;



import java.io.Serializable;
import java.util.ArrayList;

public class ChatMessages implements Serializable {

    private static final long serialVersionUID = 1l;
    private final ArrayList<MessagePacket> chat = new ArrayList<MessagePacket>();
    public ChatMessages() {}
    public boolean add(MessagePacket message){
        if(!chat.contains(message)){
            chat.add(message);
            return true;
        }
        return false;
    }
    //
    public ArrayList<MessagePacket> chat() {
        return chat;
    }
}
