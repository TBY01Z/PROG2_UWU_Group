package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.AddPlayerPacket;
import com.prog2.uwugroup.packets.RemovePlayerPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.net.InetAddress;

import static com.prog2.uwugroup.StartStageControl.rec;

public class Listener {

    public static void received(Object p){
        if(p instanceof AddPlayerPacket) {
            AddPlayerPacket packet = (AddPlayerPacket) p;
            PlayerHandler.players.put(packet.id, new Player(packet.id, packet.name));
            System.out.print(packet.name + " joined the game.");
        } else if(p instanceof RemovePlayerPacket){
            RemovePlayerPacket packet = (RemovePlayerPacket) p;
            System.out.print(PlayerHandler.players.get(packet.id).name + " has left the game.");
            PlayerHandler.players.remove(packet.id);
        } else if(p instanceof StringPacket){
            System.out.println("dir wurde ein strick geschenkt <3");
            Platform.runLater(StartStageControl::rec);

        }
    }
}
