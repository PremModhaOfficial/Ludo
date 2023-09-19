package com.game.ludo;

import java.util.Hashtable;
import java.util.Objects;
import java.util.function.Function;

public abstract class LudoPlayerAbstract {
    static Hashtable<Integer, Coordinates> pathRed;
    static Hashtable<Integer, Coordinates> pathGreen;
    static Hashtable<Integer, Coordinates> pathYellow;
    static Hashtable<Integer, Coordinates> pathBlue;

    public enum PlayerName {
        GREEN, RED, YELLOW, BLUE
    }

    public static void main(String[] args) {
        pathRed = PathCreator(1, 6, Coordinates.MoveRight, Coordinates.MoveUp, Coordinates.MoveDown, Coordinates.MoveLeft);
        pathGreen = PathCreator(8, 1, Coordinates.MoveDown, Coordinates.MoveRight, Coordinates.MoveLeft, Coordinates.MoveUp);
        pathYellow = PathCreator(13, 8, Coordinates.MoveLeft, Coordinates.MoveDown, Coordinates.MoveUp, Coordinates.MoveRight);
        pathBlue = PathCreator(6, 13, Coordinates.MoveUp, Coordinates.MoveRight, Coordinates.MoveLeft, Coordinates.MoveDown);
    }
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

    private static Coordinates modifiedReturn(Coordinates name, int xx, int yy) {
        if (xx > 0)
            name.x += xx;
        else
            name.x -= Math.abs(xx);
        if (yy > 0)
            name.y += yy;
        else
            name.y -= Math.abs(yy);
        return name;
    }

    static class Coordinates {
        int x;
        int y;

        public Coordinates(Coordinates c) {
            this.x = c.x;
            this.y = c.y;
        }

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