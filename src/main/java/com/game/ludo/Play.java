package com.game.ludo;

import java.util.Scanner;


public class Play {
    static Scanner sc = new Scanner(System.in);

    public Play(int totalPlayers) {
        if (totalPlayers >= 4) {
            totalPlayers = 4;
        }
        if (totalPlayers <= 1) {
            totalPlayers = 2;
        }
    }

    public static void main(String[] args) {
        Play game = new Play(sc.nextInt());
    }

    String[] pNames = {
            "RED",
            "YELLOW",
            "GREEN",
            "BLUE"
    };

    class Player {
        String pName;

        class Piece {
            int pNum;
            int distanceFromHome;
        }

    }

}