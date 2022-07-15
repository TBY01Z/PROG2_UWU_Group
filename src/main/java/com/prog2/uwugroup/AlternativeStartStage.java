package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AlternativeStartStage extends Application {
    private boolean isServer = true;   //hier wird bestimmt ob man server oder client ist
    //TODO: am besten im main menu nen button machen, mit dem man hosten oder joinen kann

    private TextArea messages = new TextArea();

    private NetworkConnection connection = isServer ? createServer() : createClient();

    private Parent createContent(){
        messages.setPrefHeight(550);
        TextField input = new TextField();

        input.setOnAction(event -> {
            String message = isServer ? "Server: " : "Client: ";
            message += input.getText();
            input.clear();

            messages.appendText(message = "\n");

            try{
                connection.send(message);
            } catch(Exception e){
                messages.appendText("Senden fehlgeschlagen!");
                e.printStackTrace();
            }
        });

        VBox root = new VBox(20, messages, input);
        root.setPrefSize(600, 600);
        return root;
    }

    @Override
    public void init() throws Exception {
        connection.startConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        connection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private Server createServer() {
        return new Server(8080, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient() {
        return new Client("192.168.178.36", 8080, data -> {      //hier ip vom server eingeben, wenn man client ist
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }
}

