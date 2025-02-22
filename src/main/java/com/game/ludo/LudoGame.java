package com.game.ludo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.game.ludo.LudoBoardDisplay.displayBoard;

public class LudoGame {
    @Override
    public String toString() {
        return "players: " + Arrays.stream(players.toArray()).toList() + "::";
    }

    public String[] ArrayOfPlayersNames = {
            LudoPlayer.PlayerName.RED.toString(),
            LudoPlayer.PlayerName.YELLOW.toString(),
            LudoPlayer.PlayerName.GREEN.toString(),
            LudoPlayer.PlayerName.BLUE.toString(),
    };

    ArrayList<LudoPlayer> players;

    public LudoGame(int totalPlayers, int includesBot) {
        int iterates = totalPlayers;
        totalPlayers = Math.min(totalPlayers, 4);
        players = new ArrayList<>();
        for (int i = 0; i < iterates; i++) {
            try {
                System.out.println("select colour");
                System.out.println("""
                        R red
                        Y yellow
                        G green
                        B blue
                        """);
                String playerColor = ArrayOfPlayersNames[i];
//                Scanner scanner = new Scanner(System.in);
//                switch (scanner.nextLine().toLowerCase()) {
//                    case "r" -> playerColor = ArrayOfPlayersNames[0];
//                    case "y" -> playerColor = ArrayOfPlayersNames[1];
//                    case "b" -> playerColor = ArrayOfPlayersNames[3];
//                    case "g" -> playerColor = ArrayOfPlayersNames[2];
//                    default -> System.out.println("going with default");
//                }
//
                players.add(new LudoPlayer(playerColor, totalPlayers - includesBot > 0));
                System.out.println(totalPlayers - includesBot > 0 ? "HUMAN" : "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+BOT+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                totalPlayers--;
            } catch (NullPointerException e) {
                throw new RuntimeException("only 4 max player Allowed");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        int totalPlayers = 0, totalBots = 0;
        try {
            totalPlayers = Integer.parseInt(scanner.nextLine());
            System.out.println("Of Witch How many are Robots?");
            totalBots = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("NOT A NUMBER!");
        }
        new LudoGame(Math.min(4, Math.max(2, totalPlayers)), Math.min(4, Math.max(0, totalBots))).startPlaying();
    }

    public void startPlaying() {
        displayBoard(this); // Display the game board
        while (true) {
            for (LudoPlayer currentPlayer : players) {
                int step;
                do {
                    displayBoard(this);
                    LudoBoardDisplay.displayPlayerTurn(currentPlayer, step = RollDice());
                    Piece updatedPiece = currentPlayer.takeTurn(step);
                    if (updatedPiece != null) {
                        for (LudoPlayer allPlayers : players) {
                            for (Piece piece : allPlayers.playerPieces) {
                                if (!updatedPiece.playerName.equals(piece.playerName) && updatedPiece.currentPosition.equals(piece.currentPosition)) {
                                    LudoBoardDisplay.displayCollision(piece, updatedPiece);
                                    piece.stepCount = -1;
                                    piece.updatePosition(0);
                                }
                            }
                        }
                        LudoBoardDisplay.displayPieceMoved(updatedPiece);
                    }
                } while (step == 6);
            }
        }
    }

    public int RollDice() {
        return (int) ((Math.random() * 5.9999999999) + (1));
    }
}