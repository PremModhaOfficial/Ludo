package com.game.ludo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Play {
    ArrayList<Player> players;
    Hashtable<PlayerPiece, PlayerPiece.Position> piecePosition;
    static Scanner sc = new Scanner(System.in);

    public Play(int totalPlayers) {
        players = new ArrayList<>();

        for (int i = 0; i < totalPlayers; i++) {
        }
    }

    public static void main(String[] args) {
        Play game = new Play(getPlayerNumber());
    }

    private static int getPlayerNumber() {
        System.out.print("enter number of players : ");
        int n = sc.nextInt();
        if (n>0&&n<5)
        return n;
        else
            return getPlayerNumber();
    }

}