package com.game.ludo;

import java.util.*;
import java.util.function.Function;

public class LudoPlayer {
    String playerName;
    ArrayList<Piece> playerPieces;

    Hashtable<Integer, Coordinates> pathRed;
    Hashtable<Integer, Coordinates> pathGreen;
    Hashtable<Integer, Coordinates> pathYellow;
    Hashtable<Integer, Coordinates> pathBlue;

    public LudoPlayer(String playerName) {
        constructPaths();
        this.playerName = playerName;
        Hashtable<Integer, Coordinates> myPath = selectPath(playerName);
        if (myPath == null) {
            throw new NullPointerException("null name");
        }
        playerPieces = new ArrayList<>(8);
        for (int i = 0; i < 4; i++) {
            playerPieces.add(i, new Piece(myPath, playerName));
        }

    }

    public void constructPaths() {
        pathRed = PathCreator(1, 6, Coordinates.MoveRight, Coordinates.MoveUp, Coordinates.MoveDown, Coordinates.MoveLeft);
        pathGreen = PathCreator(8, 1, Coordinates.MoveDown, Coordinates.MoveRight, Coordinates.MoveLeft, Coordinates.MoveUp);
        pathYellow = PathCreator(13, 8, Coordinates.MoveLeft, Coordinates.MoveDown, Coordinates.MoveUp, Coordinates.MoveRight);
        pathBlue = PathCreator(6, 13, Coordinates.MoveUp, Coordinates.MoveRight, Coordinates.MoveLeft, Coordinates.MoveDown);
    }

    private Hashtable<Integer, Coordinates> selectPath(String playerName) {
        Hashtable<Integer, Coordinates> myPath = null;
        switch (playerName.toUpperCase()) {
            case "YELLOW" -> myPath = pathYellow;
            case "RED" -> myPath = pathRed;
            case "BLUE" -> myPath = pathBlue;
            case "GREEN" -> myPath = pathGreen;
        }
        System.out.println(myPath);
        return myPath;
    }

    public boolean takeTurn(int step) {
        int hardChoice = 1;
        playerPieces
                .stream()
                .map(ludoPlayerPiece -> ludoPlayerPiece + " | " + ludoPlayerPiece.currentPosition)
                .forEach(System.out::print);
        System.out.println("select piece");
        Scanner scanner = new Scanner(System.in);
        Piece chosenPiece = null;
        boolean successfullyCos = false;
        while (!successfullyCos) {
            System.out.println("chose from 1 to 4 of: " + playerName + " for " + hardChoice++);
//               stringChoice = scanner.nextLine();

            List<Boolean> allCanStep = playerPieces.stream().map(piece -> piece.canmove(step)).toList();
            if (!allCanStep.contains(true))
                break;

                    switch (hardChoice+"") {
                        case "1" -> chosenPiece = playerPieces.get(0);
                        case "2" -> chosenPiece = playerPieces.get(1);
                        case "3" -> chosenPiece = playerPieces.get(2);
                        case "4" -> chosenPiece = playerPieces.get(3);
                        default -> {
                            System.out.println("enter valid input");
                            hardChoice = hardChoice + 1;
                            if (hardChoice > 4) {
                                hardChoice = 0;
                            }
                        }
                    }
            if (chosenPiece == null)
                continue;
            if (chosenPiece.positions.containsKey(step + chosenPiece.stepCount)) {
                chosenPiece.updatePosition(step);
                successfullyCos = true;
            } else {
                System.out.println("couldn't Move");
            }
        }
        return !playerPieces.stream().map(piece -> piece.reachedGoal()).toList().contains(false);
    }


    public enum PlayerName {
        GREEN, RED, YELLOW, BLUE
    }

    /**
     * for use at controller
     */

    private static Hashtable<Integer, Coordinates> PathCreator(int x, int y, Function<Coordinates, Coordinates> first, Function<Coordinates, Coordinates> second, Function<Coordinates, Coordinates> third, Function<Coordinates, Coordinates> fourth) {
        Hashtable<Integer, Coordinates> path = new Hashtable<>();
        Coordinates coordinates = new Coordinates(x, y);
        int T = 0;
        for (int i = 0; i < 5; i++)
            path.put(T++, first.apply(coordinates));
        //second Stretch
        second.apply(coordinates);
        for (int i = 0; i < 5; i++)
            path.put(T++, second.apply(coordinates));
        path.put(T++, first.apply(coordinates));
        path.put(T++, first.apply(coordinates));
        for (int i = 0; i < 6; i++)
            path.put(T++, third.apply(coordinates));
        //Third Stretch
        first.apply(coordinates);
        for (int i = 0; i < 5; i++)
            path.put(T++, first.apply(coordinates));
        path.put(T++, third.apply(coordinates));
        path.put(T++, third.apply(coordinates));
        for (int i = 0; i < 6; i++)
            path.put(T++, fourth.apply(coordinates));
        //Fourth Stretch
        third.apply(coordinates);
        for (int i = 0; i < 5; i++)
            path.put(T++, third.apply(coordinates));
        path.put(T++, fourth.apply(coordinates));
        path.put(T++, fourth.apply(coordinates));
        for (int i = 0; i < 6; i++)
            path.put(T++, second.apply(coordinates));
        //HOME
        fourth.apply(coordinates);
        for (int i = 0; i < 5; i++)
            path.put(T++, fourth.apply(coordinates));
        path.put(T++, second.apply(coordinates));
        for (int i = 0; i < 7; i++)
            path.put(T++, first.apply(coordinates));
        return path;
    }

    @Override
    public String toString() {
        return playerName;
    }

    static class Coordinates {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }


        static Function<Coordinates, Coordinates> MoveUp = coordinates -> new Coordinates(coordinates.x, coordinates.y--);
        static Function<Coordinates, Coordinates> MoveDown = coordinates -> new Coordinates(coordinates.x, coordinates.y++);
        static Function<Coordinates, Coordinates> MoveLeft = coordinates -> new Coordinates(coordinates.x--, coordinates.y);
        static Function<Coordinates, Coordinates> MoveRight = coordinates -> new Coordinates(coordinates.x++, coordinates.y);

        @Override
        public String toString() {
            return "[x=" + x + " y=" + y + "]\n";
        }
    }

}