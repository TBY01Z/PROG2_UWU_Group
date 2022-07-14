package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.AcceptPacket;
import com.prog2.uwugroup.packets.ChatPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Listener {

    public static void received(Object p){
        FXMLLoader fxmlLoader;
        if(p instanceof StringPacket){
            System.out.println("Verbindungsanfrage erhalten.");
            StringPacket packet =(StringPacket) p;
            final ActionEvent event = packet.getEvent();
            StartStageControl.setEvent(event);
            Platform.runLater(StartStageControl::request);
        } else if(p instanceof ChatPacket){
            ChatPacket.getContent();
        } else if (p instanceof AcceptPacket) {
            fxmlLoader = new FXMLLoader(StartStage.class.getResource("ChatScene.fxml"));
        }
    }
}
