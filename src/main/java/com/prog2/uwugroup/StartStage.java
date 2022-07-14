package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartStage extends Application {
    private boolean isServer = false;

    @FXML
    private TextArea messages;
    @FXML
    private TextField input;
    private NetworkConnection connection = isServer ? createServer() : createClient();

    @Override
    public void init() throws Exception{
        connection.startConnection();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartStage.class.getResource("ChatScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 580);
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception{
        connection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void sendMessage(ActionEvent event){
        String message = isServer ? "Server: " : "ClientL: ";
        message += input.getText();
        input.clear();

        messages.appendText(message + "\n");
        try {
            connection.send(message);
        } catch (Exception e) {
            messages.appendText("Senden fehlgeschlagen\n");
        }
    }

    private Server createServer(){
        return new Server(8080, data ->{
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient(){
        return new Client("192.168.178.20", 8080, data ->{
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        });
    }
}