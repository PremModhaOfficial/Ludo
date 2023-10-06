package com.game.ludo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class LudoGame {
    boolean powerMod = false;
    @Override
    public String toString() {
        return "players: " + Arrays.stream(players.toArray()).toList() + "::";
    }

    ArrayList<LudoPlayer> players;

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
            }
        }
    }

    public void setAllPositions() {
    }

    public int RollDice() {
        return (int) ((Math.random() * 5.9999999999) + (1));
//        return 56;
    }
}