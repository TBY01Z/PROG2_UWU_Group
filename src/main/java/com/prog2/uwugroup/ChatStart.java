package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatStart {
    private static boolean connection;
    public static void start() throws Exception {
        FXMLLoader fxmlLoader ;
        if(connection){
            fxmlLoader = new FXMLLoader(StartStage.class.getResource("YesStage.fxml"));
        }else {
            fxmlLoader = new FXMLLoader(StartStage.class.getResource("NoStage.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load(), 800, 580);
        Stage stage = new Stage();
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }
    public static void startE(boolean con){
        connection = con;
        try {
            start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
