package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.ChatMessages;
import com.prog2.uwugroup.packets.ChatPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Listener {

    public static void received(Object p){
        if(p instanceof StringPacket) {
            System.out.println("Verbindungsanfrage erhalten.");
            StringPacket packet = (StringPacket) p;
            final ActionEvent event = packet.getEvent();
            StartStageControl.setEvent(packet.getAdresse());
            Platform.runLater(StartStageControl::request);
        } else if (p instanceof ChatMessages) {
            ChatMessages chat = (ChatMessages) p;
            if(chat.chat().get(chat.chat().size()-1).message() =="Connect?"){
                StartStageControl.setChatMessages(chat);
                Platform.runLater(StartStageControl::chatRequest);
            } else if (chat.chat().get(chat.chat().size()-1).message() =="Connect accept") {
                StartStageControl.setChatMessages(chat);
                Platform.runLater(StartStageControl::chatWasAccepted);
            }else{
                //todo show content
            }


        } else if(p instanceof ChatPacket){
            ChatPacket.getContent();
        }
    }
}
