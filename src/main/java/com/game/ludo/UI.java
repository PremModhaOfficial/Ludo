package com.game.ludo;
public class UI {
    public static void main(String[] args) {
        int size = 15; // Size of the board
        String[][] board = new String[size][size];

        // Initialize the board with empty spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = " ";
            }
        }

        // Add players' tokens with colors and Nerd Fonts
        board[0][0] = "\u001B[31m\uE0A0\u001B[0m"; // Red player
        board[0][size - 1] = "\u001B[32m\uE0A0\u001B[0m"; // Green player
        board[size - 1][0] = "\u001B[33m\uE0A0\u001B[0m"; // Yellow player
        board[size - 1][size - 1] = "\u001B[34m\uE0A0\u001B[0m"; // Blue player

        // Display the board with colors and Nerd Fonts
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}