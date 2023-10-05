package com.game.ludo;

import java.util.ArrayList;
import java.util.Hashtable;

public class LudoGame {
    ArrayList<LudoPlayer> players;
    Hashtable<LudoPlayer.Coordinates,LudoPlayerPiece> allPositions;

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

    private void startPlaying() {
        allPositions = new Hashtable<>();
        setAllPositions();
        boolean GameWon = false;
        while (!GameWon) {
            for (LudoPlayer p : players) {
                System.out.println(p);
                int step = RollDice();
                GameWon = p.takeTurn(step);
                if (GameWon) {
                    System.out.println(p+" HAS WON!!");
                    System.exit(1);
                }
                for (LudoPlayerPiece playerPiece : p.playerPieces) {
                    LudoPlayerPiece rejected = allPositions.put(playerPiece.currentPosition,playerPiece);
                    if (rejected != null) {
                        rejected.stepCount = 0;
                        rejected.updatePosition(0);
                    }
                }
            }
        }
    }

    private void setAllPositions() {
        for (LudoPlayer player : players) {
            for (LudoPlayerPiece playerPiece : player.playerPieces) {
                LudoPlayerPiece rejected = allPositions.put(playerPiece.currentPosition,playerPiece);
                if (rejected != null) {
                    rejected.stepCount = 0;
                    rejected.updatePosition(0);
                }
            }
        }
    }

    private int RollDice() {
//        return (int) ((Math.random() * 5.9999999999) + (1));
        return 56;
    }
}