package com.game.ludo;

//import javafx.event.ActionEvent;

//import javafx.scene.shape.Circle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Controller {

    LudoGame game = null;

    @FXML
    Button DICE, R1, R2, R3, R4, G1, G2, G3, G4, B1, B2, B3, B4, Y1, Y2, Y3, Y4, curr;

    Button[] Barr = {
            DICE, curr, R1, R2, R3, R4, G1, G2, G3, G4, B1, B2, B3, B4, Y1, Y2, Y3, Y4
    };
    @FXML
    Label turnLabel;

    @FXML
    public void start() {
        if (game == null) {
            game = new LudoGame();
            //start playing
            game.allPositions = new Hashtable<>();
//          game.setAllPositions();
            {
                ArrayList<LudoPlayer> players = game.players;
                for (LudoPlayer player : players) {
                    for (Piece playerPiece : player.playerPieces) {
                        Piece rejected = game.allPositions.put(playerPiece.currentPosition, playerPiece);
                        if (rejected != null) {
                            rejected.stepCount = 0;
                            rejected.updatePosition(0);
                        }
                    }
                }
            }
            boolean GameWon = false;
            while (!GameWon) {
                for (LudoPlayer p : game.players) {
                    int step = game.RollDice();
                    System.out.println(p + " <> " + step);
                    turnLabel.setText(p + " <> " + step);
//                    GameWon = p.takeTurn(step);
                    {
                        p.playerPieces
                                .stream()
                                .map(ludoPlayerPiece -> ludoPlayerPiece + " | " + ludoPlayerPiece.currentPosition)
                                .forEach(System.out::print);
                        System.out.println("select piece");
                        Scanner scanner = new Scanner(System.in);
                        Piece chosenPiece = null;
                        boolean successfullyCos = false;
                        while (!successfullyCos) {
                            System.out.println("chose from 1 to 4 of: " + p.playerName);
                            int indexPadding = getIndexPadding(p);
                            switch (scanner.nextLine()) {
                                case "1" -> {
                                    chosenPiece = p.playerPieces.get(0);
                                    curr = Barr[indexPadding + 1];
                                }
                                case "2" -> {
                                    chosenPiece = p.playerPieces.get(1);
                                    curr = Barr[indexPadding + 2];
                                }
                                case "3" -> {
                                    chosenPiece = p.playerPieces.get(2);
                                    curr = Barr[indexPadding + 3];
                                }
                                case "4" -> {
                                    chosenPiece = p.playerPieces.get(3);
                                    curr = Barr[indexPadding + 4];
                                }

                                default -> System.out.println("enter valid input");
                            }
                            if (chosenPiece == null)
                                continue;
                            if (chosenPiece.positions.containsKey(step + chosenPiece.stepCount)) {
                                chosenPiece.updatePosition(step);
                                curr.setLayoutY(chosenPiece.currentPosition.y);
                                curr.setLayoutX(chosenPiece.currentPosition.x);
                                successfullyCos = true;
                            } else {
                                System.out.println("couldn't Move");
                            }
                        }
                        if (GameWon) {
                            System.out.println(p + " HAS WON!!");
                            break;
                        }
                        for (Piece playerPiece : p.playerPieces) {
                            Piece rejected = game.allPositions.put(playerPiece.currentPosition, playerPiece);
                            if (rejected != null) {
                                if (!rejected.playerName.equals(p.playerName)) {
                                    rejected.setStepCount(0);
                                    rejected.updatePosition(0);
                                }
                            }
                        }
                    }
                }//Ended playing
            }
        }
    }

    private static int getIndexPadding(LudoPlayer p) {
        int indexPadding = 0;
        switch (p.playerName.toLowerCase().charAt(0)) {
            case 'r' -> {
                indexPadding = 1;
            }
            case 'g' -> {
                indexPadding = 5;
            }
            case 'b' -> {
                indexPadding = 9;
            }
            case 'y' -> {
                indexPadding = 13;
            }
        }
        return indexPadding;
    }
}