package com.prog2.uwugroup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private TextFlow textFlow = new TextFlow();
    @FXML
    private TextField textField = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
