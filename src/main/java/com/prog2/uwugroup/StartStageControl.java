package com.prog2.uwugroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class StartStageControl implements Initializable {
    private final int IP_MAX_VALUE = 255;
    private final int IP_MIN_VALUE = 0;
    private final String programTitle = "UWU Gruppe";
    private final Integer[] arrayData = {0,1,2,3};
    private final char[] notAllowed = {};
    private Integer networkPort = 0;

    @FXML
    private Label networkLabel = new Label();
    @FXML
    private TextField ipField1 = new TextField();
    @FXML
    private TextField ipField2 = new TextField();
    @FXML
    private TextField ipField3 = new TextField();
    @FXML
    private TextField ipField4 = new TextField();
    public void Quit(ActionEvent event) {
        javafx.application.Platform.exit();
    }


    public void showIP(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(programTitle);
        alert.setHeaderText("Your IP");
        String s ="Your IP is: 127.0.0.1 ";
        alert.setContentText(s);
        alert.show();
    }
    private void update(){
        networkLabel.setText(": " + networkPort);
    }
    public void networkHelp(ActionEvent event) {
        List<Integer> dialogData = Arrays.asList(arrayData);

        ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle(programTitle);
        dialog.setHeaderText("Select your Port");

        Optional<Integer> result = dialog.showAndWait();
        int selected = 0;

        if (result.isPresent()) {

            selected = result.get();
        }

        networkPort = selected;
        update();
    }

    public void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your IP");
        alert.setHeaderText("Your IP");
        String s ="Your IP is: 127.0.0.1 ";
        alert.setContentText(s);
        alert.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ipField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.matches("\\d+")){
                        int change = Integer.parseInt(newValue);
                        if(!(change > IP_MIN_VALUE && change <= IP_MAX_VALUE)){
                            ipField1.setText(oldValue);
                        }
                    } else if (!newValue.isEmpty()) {
                        ipField1.setText(oldValue);
                    }
                }
        });
        //noinspection DuplicatedCode
        ipField2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("\\d+")){
                    int change = Integer.parseInt(newValue);
                    if(!(change >= IP_MIN_VALUE && change <= IP_MAX_VALUE)){
                        ipField2.setText(oldValue);
                    }
                } else if (!newValue.isEmpty()) {
                    ipField2.setText(oldValue);
                }
            }
        });
        //noinspection DuplicatedCode
        ipField3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("\\d+")){
                    int change = Integer.parseInt(newValue);
                    if(!(change >= IP_MIN_VALUE && change <= IP_MAX_VALUE)){
                        ipField3.setText(oldValue);
                    }
                } else if (!newValue.isEmpty()) {
                    ipField3.setText(oldValue);
                }
            }
        });
        ipField4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("\\d+")){
                    int change = Integer.parseInt(newValue);
                    if(!(change > IP_MIN_VALUE && change < IP_MAX_VALUE)){
                        ipField4.setText(oldValue);
                    }
                } else if (!newValue.isEmpty()) {
                    ipField4.setText(oldValue);
                }
            }
        });
    }
}