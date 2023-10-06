package com.game.ludo;

public class LudoGameDisplay {
    public static void displayGame(LudoGame game) {
        System.out.println("Ludo Game");
        System.out.println("---------");
        System.out.println("Players:");
        for (LudoPlayer player : game.players) {
            System.out.println(player);
        }
        System.out.println("---------");
    }

    public static void displayPlayerTurn(LudoPlayer player, int step) {
        System.out.println(player + "'s Turn: Rolled a " + step);
    }

    public static void displayPieceMoved(Piece piece) {
        System.out.println(piece.playerName + " moved to " + piece.currentPosition);
    }

    public static void displayCollision(Piece piece1, Piece piece2) {
        System.out.println("Collision between " + piece1 + " and " + piece2);
    }

    public static void displayWinner(LudoPlayer winner) {
        System.out.println("Congratulations! " + winner + " has won the game!");
    }
}