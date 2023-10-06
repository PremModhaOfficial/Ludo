package com.game.ludo;

import java.util.ArrayList;
import java.util.Arrays;

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
                players.add(new LudoPlayer(ArrayOfPlayersNames[i], totalPlayers - includesBot > 0));
                System.out.println(totalPlayers - includesBot > 0 ? "HUMAN" : "BOT+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                totalPlayers--;
            } catch (NullPointerException e) {
                throw new RuntimeException("only 4 max player Allowed");
            }
        }
    }

    public static void main(String[] args) {
        new LudoGame(3, 2).startPlaying();
    }

    public void startPlaying() {
        while (true) {
            displayBoard(this); // Display the game board

            for (LudoPlayer currentPlayer : players) {
                int step = RollDice();
                LudoBoardDisplay.displayPlayerTurn(currentPlayer, step);

                Piece updatedPiece = currentPlayer.takeTurn(step);
                if (updatedPiece != null) {
                    for (LudoPlayer allPlayers : players) {
                        for (Piece piece : allPlayers.playerPieces) {
                            if (!updatedPiece.playerName.equals(piece.playerName) && updatedPiece.currentPosition.equals(piece.currentPosition)) {
                                LudoBoardDisplay.displayCollision(piece, updatedPiece);
                                piece.stepCount = 0;
                                piece.updatePosition(0);
                            }
                        }
                    }
                }
                if (updatedPiece != null)
                    LudoBoardDisplay.displayPieceMoved(updatedPiece);
            }
        }
    }

    public int RollDice() {
        return (int) ((Math.random() * 5.9999999999) + (1));
    }
}