package com.prog2.uwugroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateClientUIControl implements Initializable {
    @FXML
    private static Button attachment = new Button("+");
    private String title = "UWU GRUPPE";
    private String userName;
    private InetAddress ip;
    private NewClient client;
    private String setUserName(){
        String value = "username";
        TextInputDialog dialog;
        dialog = new TextInputDialog(value);
        dialog.setTitle(title);
        dialog.setHeaderText("Choice your Username for the Chat.");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            value = result.get();
        }
        return value;
    }

    private void setIP() {
        Dialog<IP> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("This is a custom dialog. Enter info and \n" +
                "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);

        Label label1 = new Label("IP: ");
        Label label2 = new Label(":");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        TextField text4 = new TextField();
        TextField port = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(text2, 3, 1);
        grid.add(text3, 4, 1);
        grid.add(text4, 5, 1);
        grid.add(label2, 6, 1);
        grid.add(port, 7, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, IP>() {
            @Override
            public IP call(ButtonType b) {

                if (b == buttonTypeOk) {

                    return new IP(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()), Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()), Integer.parseInt(port.getText()));
                }

                return null;
            }
        });

        Optional<IP> result = dialog.showAndWait();

        if (result.isPresent()) {

            IP workingIP = result.get();
            try {
                client = new NewClient(new Socket(String.valueOf(workingIP.firstOctet()) + String.valueOf(workingIP.secondOctet()) + String.valueOf(workingIP.thirdOctet()) + String.valueOf(workingIP.fourthOctet()), workingIP.port()), userName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
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
        userName = setUserName();
        setIP();
    }

    public void addFile(ActionEvent event) {

    }
}
