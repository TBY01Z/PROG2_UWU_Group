package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.AddPlayerPacket;
import com.prog2.uwugroup.packets.ChatPacket;
import com.prog2.uwugroup.packets.RemovePlayerPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.net.InetAddress;

import static com.prog2.uwugroup.StartStageControl.rec;

public class Listener {

    public static void received(Object p){
        if(p instanceof StringPacket){
            System.out.println("Verbindungsanfrage erhalten.");
            Platform.runLater(StartStageControl::rec);
        } else if(p instanceof ChatPacket){
            ChatPacket.getContent();
        }
    }
}
