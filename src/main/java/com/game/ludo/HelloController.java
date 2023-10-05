package com.game.ludo;

//import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
//import javafx.scene.shape.Circle;


public class HelloController extends LudoPlayer {
    @FXML
    private Label welcomeText = new Label("LOL");


    @FXML
    Button DICE;
    @FXML
    Circle red3;

static int k = 0;

    public HelloController() {
        super(PlayerName.RED.name());
    }

    @FXML
    protected void turnG(ActionEvent e) throws InterruptedException {
//        constructPaths();
//        k += RollDice();
//        try {
//            red3.setCenterX(pathRed.get(k).y*29);
//        } catch (NullPointerException err){
//            err.getMessage();
//        }
    }

    @FXML
    Button lol;
    @FXML
    protected void setRed3(ActionEvent e) throws InterruptedException {
//        constructPaths();
//        int step = RollDice();
//        if (!pathGreen.containsKey(k+step)) {
//        } else {
//        k += 1;
//            lol.setLayoutY((pathYellow.get(k).y*29.8)+26);
//            lol.setLayoutX((pathYellow.get(k).x*29.8)+26);
//        }
    }

    private Integer RollDice() {
       return (int) (Math.random() * 5.999999) + 1;
    }

}