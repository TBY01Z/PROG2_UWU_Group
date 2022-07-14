package com.prog2.uwugroup;

import com.prog2.uwugroup.packets.ChatPacket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private TextArea textArea = new TextArea();
    @FXML
    private TextField textField = new TextField();
    private String msg;
    private String senderIdentity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendMsg(ActionEvent event){
        msg = textField.getText();
        ChatPacket message = new ChatPacket(msg);
        textArea.appendText(msgFormatter(message.getContent()));
        textField.clear();
    }

    public String msgFormatter(String msg){
        StringBuilder sb = new StringBuilder(msg);
        return this.senderIdentity + ": " + sb.append("\n\n");
    }


    public String getSenderIdentity(){
        return senderIdentity;
    }

    public void setSenderIdentity(String senderIdentity){
        this.senderIdentity = senderIdentity;
    }

}
