package com.game.ludo;

public class LudoBoardDisplay {
        // Initialize the board with spaces
        public static void displayBoard(LudoGame game) {
            System.out.println("Ludo Board");
            System.out.println("---------");

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
            char[][] board = new char[ludoBoardLayout.length][ludoBoardLayout[0].length()];
            for (int row = 0; row < ludoBoardLayout.length; row++) {
                for (int col = 0; col < ludoBoardLayout[0].length(); col++) {
                    board[row][col] = ludoBoardLayout[row].charAt(col);
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
                        board[centerY][centerX] = player.playerName.charAt(0);
                    } else {
                        player.pieceAtHome++;
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

    public static void main(String[] args) {
        LudoGame game = new LudoGame(4, 0); // Example game with 4 players
        displayBoard(game);
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
}