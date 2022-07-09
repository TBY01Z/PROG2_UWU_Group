package com.prog2.uwugroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    private float health = 10.0f;   //maxHealth 10.0
    @FXML
    private Label actionLabel = new Label();
    @FXML
    private ProgressBar healthBar = new ProgressBar();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        healthBar.setStyle("-fx-accent: green;");
        actionLabel.setStyle("-fx-accent: blue");
    }

    public void onPlayerMoveUp(ActionEvent event){
        actionLabel.setText("Spieler hat sich nach Oben bewegt.");
    }
    public void onPlayerMoveDown(ActionEvent event){
        actionLabel.setText("Spieler hat sich nach Unten bewegt.");
    }
    public void onPlayerMoveLeft(ActionEvent event){
        actionLabel.setText("Spieler hat sich nach Links bewegt.");
    }
    public void onPlayerMoveRight(ActionEvent event){
        actionLabel.setText("Spieler hat sich nach Rechts bewegt.");
    }
    public void onPlayerAttack(ActionEvent event){
        actionLabel.setText("Spieler attackiert!");
    }
    public void onPlayerTookDamage(){
        if(health == 0.0f){
            actionLabel.setText("Player ist gestorben!");
        } else{
            hpDecr(0.2f);
            actionLabel.setText("Player hat 0.2 Schaden bekommen!");
        }
    }
    public void onPlayerHeal(ActionEvent event){
        hpIncr(2.0f);
        actionLabel.setText("Player wurde um 2 Lebenspunkte geheilt.");
    }
    public float hpDecr(float decrVal){
        if(health != 0.0f && health > decrVal) {
            return health - decrVal;
        } else{
            return health = 0.0f;
        }
    }
    public float hpIncr(float incrVal){
        if(health < 10 && health < 10 + incrVal){
            return health + incrVal;
        } else{
            return health;
        }
    }

    public void onEnterChat(ActionEvent event){
        MyIO.loadXML(event, "ChatScene.fxml");
    }
    public void onApplicationQuit(ActionEvent event){
        javafx.application.Platform.exit();
    }
}
