package com.game.ludo;

import java.util.ArrayList;
import java.util.Arrays;

public class LudoGame {
    boolean powerMod = false;

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

    public LudoGame(int totalPlayers,int includesBot) {
        int iterates = totalPlayers;
        totalPlayers = Math.min(totalPlayers, 4);
        players = new ArrayList<>();
        for (int i = 0; i < iterates; i++) {
            try {
                players.add(new LudoPlayer(ArrayOfPlayersNames[i],totalPlayers-includesBot>0));
                System.out.println(totalPlayers-includesBot>0?"HUMAN":"BOT+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                totalPlayers--;
            } catch (NullPointerException e) {
                throw new RuntimeException("only 4 max player Allowed");
            }
        }
    }

    public static void main(String[] args) {
        new LudoGame(3,2).startPlaying();
    }

    public void startPlaying() {
        setAllPositions();
        int count = 0;
        while (true) {
            for (LudoPlayer currentPlayer : players) {
                int step = RollDice();
                System.out.println(currentPlayer + " <> " + step);
                Piece updatedPiece = currentPlayer.takeTurn(step);
                if (updatedPiece != null) {
                    for (LudoPlayer allPlayers : players) {
                        for (Piece piece : allPlayers.playerPieces) {
                            if (!updatedPiece.playerName.equals(piece.playerName) && updatedPiece.currentPosition.x == piece.currentPosition.x && updatedPiece.currentPosition.y == piece.currentPosition.y) {
                                System.out.println(
                                        "coalition ------------------------------------------- " +
                                                count++ + " " + piece + " >< " + updatedPiece);
                                piece.stepCount = 0;
                                piece.updatePosition(0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setAllPositions() {
    }

    public int RollDice() {
        return (int) ((Math.random() * 5.9999999999) + (1));
    }
}