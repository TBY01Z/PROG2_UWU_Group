package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartStage extends Application {
    private static Server server;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartStage.class.getResource("StartStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 580);
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        server = new Server(8080);
        server.start();
        launch();

    }

    public static Server server() {
        return server;
    }

    public static void setServer(Server server) {
        StartStage.server = server;
    }
}