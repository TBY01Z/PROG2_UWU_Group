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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendMsg(ActionEvent event){
        msg = textField.getText();
        new ChatPacket(msg);
        textArea.appendText(msg);
    }

}
