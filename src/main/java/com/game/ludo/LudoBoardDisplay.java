package com.game.ludo;
import java.util.Hashtable;

public class LudoBoardDisplay {
        // Initialize the board with spaces
        public static void displayBoard(LudoGame game) {
            System.out.println("Ludo Board");
            System.out.println("---------");
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            String ANSI_GREEN = "\u001B[32m";
            String ANSI_BLUE = "\u001B[34m";
            String ANSI_YELLOW = "\u001B[33m";

            // Define the Ludo board layout as an array of strings
            String[] ludoBoardLayout = {
                    "╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬",
                    "║                       ║   ║   ║   ║                       ║",
                    "║      red.red.red      ║═══╬═══╬═══║   green.green.green   ║",
                    "║    red.red.red.red    ║   ║   ║   ║   green.green.green   ║",
                    "║   red.         red.   ║═══╬═══╬═══║   gree                ║",
                    "║   red.         red.   ║   ║   ║   ║   gree                ║",
                    "║   red.red.red.red.    ║═══╬═══╬═══║   gree      green.g   ║",
                    "║   red.red.red.red     ║   ║   ║   ║   gree      green.g   ║",
                    "║   red.  red.r         ║═══╬═══╬═══║   gree          gre   ║",
                    "║   red.     red.r      ║   ║   ║   ║   green.green.green   ║",
                    "║   red.        red.r   ║═══╬═══╬═══║   green.green.green   ║",
                    "║                       ║   ║   ║   ║                       ║",
                    "╬═══╦═══╦═══╦═══╦═══╦═══╬═══╬═══╬═══╬═══╦═══╦═══╦═══╦═══╦═══╬",
                    "║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║",
                    "╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬",
                    "║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║",
                    "╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬",
                    "║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║   ║",
                    "╬═══╩═══╩═══╩═══╩═══╩═══╬═══╬═══╬═══╬═══╩═══╩═══╩═══╩═══╩═══╬",
                    "║                       ║   ║   ║   ║                       ║",
                    "║   blue.blue.blue.b    ║═══╬═══╬═══║   yellow     yello    ║",
                    "║   blue.blue.blue.bl   ║   ║   ║   ║    yellow   yello     ║",
                    "║   blu           blu   ║═══╬═══╬═══║     yellow yello      ║",
                    "║   blue          bl    ║   ║   ║   ║       yellow.ye       ║",
                    "║   blue.blue.blue.     ║═══╬═══╬═══║        yellow.        ║",
                    "║   blue.blue.blue.bl   ║   ║   ║   ║         yello         ║",
                    "║   blu           blu   ║═══╬═══╬═══║          yel          ║",
                    "║   blue          blu   ║   ║   ║   ║          yel          ║",
                    "║   blue.blue.blue.b    ║═══╬═══╬═══║          yel          ║",
                    "║                       ║   ║   ║   ║                       ║",
                    "╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬"
            };

            // Initialize the board with spaces
            String[][] board = new String[ludoBoardLayout.length][ludoBoardLayout[0].length()];
            for (int row = 0; row < ludoBoardLayout.length; row++) {
                for (int col = 0; col < ludoBoardLayout[0].length(); col++) {
                    board[row][col] = ludoBoardLayout[row].charAt(col)+"";
                }
            }

            // Place players' pieces on the board
            for (LudoPlayer player : game.players) {
                for (Piece piece : player.playerPieces) {
                    LudoPlayer.Coordinates position = piece.currentPosition;
                    if (position.x >= 0 && position.x < board.length && position.y >= 0 && position.y < board[0].length) {
                        // Calculate the center position within each box
                        int centerX = position.x * 4 + 2;
                        int centerY = position.y * 2 + 1;

                        // Place the player character in the center
                        board[centerY][centerX] = getPlayerColor(player.playerName) + player.playerName.charAt(0) + ANSI_RESET;
                    }
                }
            }

            // Display the board
            for (int row = 0; row < ludoBoardLayout.length; row++) {
                for (int col = 0; col < ludoBoardLayout[0].length(); col++) {
                    System.out.print(board[row][col]);
                }
                System.out.println();
            }

            System.out.println("---------");
        }

    private static void highlightMoveCells(LudoGame game, char[][] board) {
        for (LudoPlayer player : game.players) {
            String playerColor = getPlayerColor(player.playerName);
            char playerInitial = player.playerName.charAt(0);

            for (Piece piece : player.playerPieces) {
                LudoPlayer.Coordinates position = piece.currentPosition;
                int step = game.RollDice();

                if (piece.canMove(step)) {
                    LudoPlayer.Coordinates newPosition = piece.positions.get(piece.stepCount + step);
                    board[newPosition.x][newPosition.y] = playerInitial;
                }
            }
        }
    }

    private static String getPlayerColor(String playerName) {
        switch (playerName.toUpperCase()) {
            case "R":
                return "\u001B[31m"; // Red color
            case "G":
                return "\u001B[32m"; // Green color
            case "Y":
                return "\u001B[33m"; // Yellow color
            case "B":
                return "\u001B[34m"; // Blue color
            default:
                return "\u001B[0m"; // Default to reset color
        }
    }

    public static void main(String[] args) {
        LudoGame game = new LudoGame(4, 0); // Example game with 4 players
        displayBoard(game);
    }
    public static void displayPlayerTurn(LudoPlayer player, int step) {
//        System.out.println(player + "'s Turn: Rolled a " + step);
    }

    public static void displayPieceMoved(Piece piece) {
//        System.out.println(piece.playerName + " moved to " + piece.currentPosition);
    }

    public static void displayCollision(Piece piece1, Piece piece2) {
//        System.out.println("Collision between " + piece1 + " and " + piece2);
    }

    public static void displayWinner(LudoPlayer winner) {
//        System.out.println("Congratulations! " + winner + " has won the game!");
    }
}