package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.AddPlayerPacket;
import com.prog2.uwugroup.packets.RemovePlayerPacket;
import com.prog2.uwugroup.packets.StringPacket;
import javafx.scene.control.Alert;

import java.net.InetAddress;

public class Listener {

    public void received(Object p){
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UwU");
            alert.setHeaderText("Message");
            String s = null;
            alert.setContentText("dir wurde ein strick geschenkt <3");
            alert.show();
        }
    }

}
