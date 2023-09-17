package com.game.ludo;

//import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.Map;
//import javafx.scene.shape.Circle;


public class HelloController extends LudoPlayerAbstract{
    @FXML
    private Label welcomeText = new Label("LOL");


    @FXML
    Button DICE;
    @FXML
    Circle red3;

static int k = 0;
    @FXML
    protected void turnG(ActionEvent e) throws InterruptedException {
        mainEms();
        k += RollDice();
        try {
            red3.setCenterX(pathRed.get(k).y*29);
        } catch (NullPointerException err){
            err.getMessage();
        }
    }

    @FXML
    Button lol;
    @FXML
    protected void setRed3(ActionEvent e) throws InterruptedException {
        mainEms();
        int step = RollDice();
        if (!pathRed.containsKey(k+step)) {
        } else {
        k += step;
            lol.setLayoutY((pathRed.get(k).y*29.7)+26);
            lol.setLayoutX((pathRed.get(k).x*29.7)+26);
        }
    }

    private Integer RollDice() {
       return (int) (Math.random() * 5.999999) + 1;
    }

}