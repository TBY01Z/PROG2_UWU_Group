package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.ChatPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Listener {

    public static void received(Object p){
        if(p instanceof StringPacket){
            System.out.println("Verbindungsanfrage erhalten.");
            StringPacket packet =(StringPacket) p;
            final ActionEvent event = packet.getEvent();
            StartStageControl.setEvent(event);
            Platform.runLater(StartStageControl::request);
        } else if(p instanceof ChatPacket){
            ChatPacket.getContent();
        }
    }
}
