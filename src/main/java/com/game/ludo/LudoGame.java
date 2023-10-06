package com.game.ludo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class LudoGame {
    @Override
    public String toString() {
        return "players: " + Arrays.stream(players.toArray()).toList() + "::";
    }

    ArrayList<LudoPlayer> players;
    Hashtable<LudoPlayer.Coordinates, Piece> allPositions;

    public LudoGame() {
        players = new ArrayList<>();
        players.add(new LudoPlayer("red"));
        players.add(new LudoPlayer("yellow"));
        players.add(new LudoPlayer("green"));
        players.add(new LudoPlayer("blue"));
    }

    public static void main(String[] args) {
        new LudoGame().startPlaying();
    }

    public void startPlaying() {
        allPositions = new Hashtable<>();
        setAllPositions();
        boolean GameWon = false;
        while (!GameWon) {
            for (LudoPlayer p : players) {
                int step = RollDice();
                System.out.println(p + " <> " + step);
                GameWon = p.takeTurn(step);
                if (GameWon) {
                    System.out.println(p+" HAS WON!!");
                    break;
                }
                for (Piece playerPiece : p.playerPieces) {
                    Piece rejected = allPositions.put(playerPiece.currentPosition,playerPiece);
                    if (rejected != null) {
                        if (!rejected.playerName.equals(p.playerName)) {
                            rejected.setStepCount(0);
                            rejected.updatePosition(0);
                        }
                    }
                }
            }
        }
    }

    public void setAllPositions() {
        for (LudoPlayer player : players) {
            for (Piece playerPiece : player.playerPieces) {
                Piece rejected = allPositions.put(playerPiece.currentPosition,playerPiece);
                if (rejected != null) {
                    rejected.stepCount = 0;
                    rejected.updatePosition(0);
                    System.out.println(player + " has send " + rejected + " home");
                }
            }
        }
    }

    public int RollDice() {
        return (int) ((Math.random() * 5.9999999999) + (1));
    }
}