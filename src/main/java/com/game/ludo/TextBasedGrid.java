package com.game.ludo;

public class TextBasedGrid {

    public static void main(String[] args) {
        // ANSI escape codes for colors
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";

        // Define the grid size
        int gridSize = 3;

        // Create a 2D array to represent the grid
        String[][] grid = new String[gridSize][gridSize];

        // Initialize the grid with cell colors
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if ((row + col) % 2 == 0) {
                    grid[row][col] = red;
                } else {
                    grid[row][col] = green;
                }
            }
        }

        // Display the grid
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                System.out.print(grid[row][col] + "Cell " + (row * gridSize + col + 1) + reset + "\t");
            }
            System.out.println(); // Move to the next row
        }

        // You can add more logic and user interaction here

        // Clean up and exit
        System.exit(0);
    }
}