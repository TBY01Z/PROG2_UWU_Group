package com.prog2.uwugroup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

import static com.prog2.uwugroup.ClientHandler.clientHandlers;


public class ServerControl implements Initializable {

    @FXML
    private Label portLabel = new Label();
    @FXML
    private TableView userTable = new TableView();
    @FXML
    private Label ipLabel = new Label();
    private final Integer[] arrayData = {2564, 2556, 8080, 8443, 9900, 9990};
    private final List<Integer> dialogData = Arrays.asList(arrayData);
    private ObservableList<Integer> choiceData = FXCollections.observableList(dialogData);
    private ObservableList<ClientHandler> clientsList = FXCollections.observableArrayList(clientHandlers);
    private static Integer selectedPort;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setItems(clientsList);

        try {
            ipLabel.setText(InetAddress.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getHostAddress());    //TODO: remove later!
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void kickClient(ActionEvent event) {
        
    }

    public void startServer() throws IOException {
        NewServer server = new NewServer(new ServerSocket(selectedPort));
        server.startServer();
    }

    public void closeServer(ActionEvent event) {
        try {
            NewServer server = new NewServer(new ServerSocket(selectedPort));
            server.closeServer();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void portSelect(ActionEvent event){
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
        //StartStage.server().changePort(networkPort);
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
}
