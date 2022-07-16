package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.prog2.uwugroup.ClientHandler.clientHandlers;

public class ServerStageStart extends Application implements Initializable{

    private ServerSocket serverSocket;
    @FXML
    private Label portLabel = new Label();
    @FXML
    private Label ipLabel = new Label();
    private final Integer[] arrayData = {2564, 2556, 8080, 8443, 9900, 9990};
    private final List<Integer> dialogData = Arrays.asList(arrayData);
    private ObservableList<Integer> choiceData = FXCollections.observableList(dialogData);
    private ObservableList<ClientHandler> clientsList = FXCollections.observableArrayList(clientHandlers);
    private static Integer selectedPort = 8080;

    public ServerStageStart(ServerSocket serverSocket){

        this.serverSocket = serverSocket;
    }
    public ServerStageStart(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portLabel.setText(String.valueOf(selectedPort));
        try {
            ipLabel.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void portSelect(ActionEvent event) throws IOException {
        List<Integer> dialogData = Arrays.asList(arrayData);

        ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("UWU Gruppe");
        dialog.setHeaderText("Select your Port");

        Optional<Integer> result = dialog.showAndWait();
        int selected = 8080;

        if (result.isPresent()) {

            selected = result.get();
        }

        selectedPort = selected;
        portLabel.setText(selectedPort.toString());

    }

    public void aboutInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UWU Gruppe");
        alert.setHeaderText("About");
        String s = "Dies ist eine Chat-Anwendung, geschrieben von Niclas Rieckers und Mark Fischer." +
                "Diese Anwendung wurde im Rahmen des Moduls PROG2 entwickelt";//TODO: change about
        alert.setContentText(s);
        alert.show();
    }

    public void startServer(){
        try{
            this.serverSocket = new ServerSocket(selectedPort);
            Server server = new Server(serverSocket);
            Thread thread0 = new Thread(server);
            thread0.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closeServer(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

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
        FXMLLoader fxmlLoader = new FXMLLoader(ServerStageStart.class.getResource("Server.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 580);
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }
}
