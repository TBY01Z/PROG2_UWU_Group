package com.prog2.uwugroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateClientUIControl implements Initializable {
    @FXML
    private static Button attachment = new Button("+");
    @FXML
    private static TextArea messages = new TextArea("");
    @FXML
    private static TextField input = new TextField();
    private final int IP_MAX_VALUE = 255;
    private final int IP_MIN_VALUE = 0;
    private boolean[] ipcheck = {false, false, false, false};
    private String title = "UWU GRUPPE";
    private String userName;

    private NewClient client;

    public static void appendChat(String msgFromChat) {
        messages.appendText(msgFromChat);
    }

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
        TextField ipField1 = new TextField();
        TextField ipField2 = new TextField();
        TextField ipField3 = new TextField();
        TextField ipField4 = new TextField();
        TextField port = new TextField();
        ipField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d+")) {
                    int change = Integer.parseInt(newValue);
                    if (!(change > IP_MIN_VALUE && change <= IP_MAX_VALUE)) {
                        ipField1.setText(oldValue);
                    } else {
                        ipcheck[0] = true;

                    }
                } else if (!newValue.isEmpty()) {
                    ipField1.setText(oldValue);
                    ipcheck[0] = false;

                } else {
                    ipcheck[0] = false;

                }
            }
        });
        //noinspection DuplicatedCode
        ipField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d+")) {
                    int change = Integer.parseInt(newValue);
                    if (!(change >= IP_MIN_VALUE && change <= IP_MAX_VALUE)) {
                        ipField2.setText(oldValue);
                    } else {
                        ipcheck[1] = true;

                    }
                } else if (!newValue.isEmpty()) {
                    ipField2.setText(oldValue);
                    ipcheck[1] = false;

                } else {
                    ipcheck[1] = false;

                }
            }
        });
        //noinspection DuplicatedCode
        ipField3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d+")) {
                    int change = Integer.parseInt(newValue);
                    if (!(change >= IP_MIN_VALUE && change <= IP_MAX_VALUE)) {
                        ipField3.setText(oldValue);
                    } else {
                        ipcheck[2] = true;

                    }
                } else if (!newValue.isEmpty()) {
                    ipField3.setText(oldValue);
                    ipcheck[2] = false;

                } else {
                    ipcheck[2] = false;

                }
            }
        });
        ipField4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d+")) {
                    int change = Integer.parseInt(newValue);
                    if (!(change > IP_MIN_VALUE && change < IP_MAX_VALUE)) {
                        ipField4.setText(oldValue);
                    } else {
                        ipcheck[3] = true;

                    }
                } else if (!newValue.isEmpty()) {
                    ipField4.setText(oldValue);
                    ipcheck[3] = false;

                } else {
                    ipcheck[3] = false;

                }
            }
        });
        port.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d+")) {
                    int change = Integer.parseInt(newValue);
                } else if (!newValue.isEmpty()) {
                    ipField2.setText(oldValue);
                }
            }
        });

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(ipField1, 2, 1);
        grid.add(ipField2, 3, 1);
        grid.add(ipField3, 4, 1);
        grid.add(ipField4, 5, 1);
        grid.add(label2, 6, 1);
        grid.add(port, 7, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, IP>() {
            @Override
            public IP call(ButtonType b) {

                if (b == buttonTypeOk) {

                    return new IP(Integer.parseInt(ipField1.getText()), Integer.parseInt(ipField2.getText()), Integer.parseInt(ipField3.getText()), Integer.parseInt(ipField4.getText()), Integer.parseInt(port.getText()));
                }

                return null;
            }
        });

        Optional<IP> result = dialog.showAndWait();

        if (result.isPresent()) {

            IP workingIP = result.get();
            try {
                client = new NewClient(new Socket(String.valueOf(workingIP.firstOctet()) +'.'+ String.valueOf(workingIP.secondOctet()) +'.'+ String.valueOf(workingIP.thirdOctet()) +'.'+ String.valueOf(workingIP.fourthOctet()), workingIP.port()), userName);
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
        client.listenForMessage();
    }

    public void sendMessage(ActionEvent event) {
        String msg = input.getText();
        messages.appendText(userName + " :\n");
        messages.appendText(msg);
        client.sendMessage(msg);
    }
}
