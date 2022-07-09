package com.prog2.uwugroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MyIO {

    public static void loadXML(ActionEvent event, String source) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StartStage.class.getResource(source));
            root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
