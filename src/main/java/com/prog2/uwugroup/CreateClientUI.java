package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Eine Klasse die die GUI unseres Clients startet
 * @author Niclas & Mark
 */
public class CreateClientUI extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateClientUI.class.getResource("CreateClientUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


}
