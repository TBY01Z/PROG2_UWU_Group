package com.prog2.uwugroup.packets;

import java.io.Serializable;

public class ChatPacket implements Serializable {
    private static final long serialVersionUID = 1l;
    private int id;
    private static String content;

    public ChatPacket(String content){
        this.content = content;
    }

    public static String getContent(){
        return content;
    }
}
