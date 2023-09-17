package com.game.ludo;

import java.util.Hashtable;
import java.util.Map;
import java.util.function.Function;

public abstract class LudoPlayerAbstract {
    static Hashtable<Integer, Coordinates> pathRed;

    public enum PlayerName {
        GREEN, RED, YELLOW, BLUE
    }

    public static void main(String[] args) {
        pathRed = new Hashtable<>();
        RedPath(pathRed);
        for (Map.Entry<Integer, Coordinates> l : pathRed.entrySet()) {
            System.out.print(l.getKey() + " " + l.getValue());
        }
    }

    public static void mainEms() {
        pathRed = new Hashtable<>();
        RedPath(pathRed);
//        for (Map.Entry<Integer, Coordinates> l : pathRed.entrySet()) {
//            System.out.print(l.getKey() + " " + l.getValue());
//        }
    }

    private static void RedPath(Hashtable<Integer, Coordinates> pathRed) {
        Coordinates Red = new Coordinates(1, 6);
        int T = 0;

        for (int i = 0; i < 5; i++)
            pathRed.put(T++, Coordinates.MoveRight.apply(Red));


        //second Stretch
        Coordinates.MoveUp.apply(Red);
//        Coordinates.MoveRight.apply(Red);
        for (int i = 0; i < 5; i++)
            pathRed.put(T++, Coordinates.MoveUp.apply(Red));
        pathRed.put(T++, Coordinates.MoveRight.apply(Red));
        pathRed.put(T++, Coordinates.MoveRight.apply(Red));
        for (int i = 0; i < 6; i++)
            pathRed.put(T++, Coordinates.MoveDown.apply(Red));

        //Third Stretch
        Coordinates.MoveRight.apply(Red);
        for (int i = 0; i < 5; i++)
            pathRed.put(T++, Coordinates.MoveRight.apply(Red));
        pathRed.put(T++, Coordinates.MoveDown.apply(Red));
        pathRed.put(T++, Coordinates.MoveDown.apply(Red));
        for (int i = 0; i < 6; i++)
            pathRed.put(T++, Coordinates.MoveLeft.apply(Red));

        //Fourth Stretch
        Coordinates.MoveDown.apply(Red);
        for (int i = 0; i < 5; i++)
            pathRed.put(T++, Coordinates.MoveDown.apply(Red));
        pathRed.put(T++, Coordinates.MoveLeft.apply(Red));
        pathRed.put(T++, Coordinates.MoveLeft.apply(Red));
        for (int i = 0; i < 6; i++)
            pathRed.put(T++, Coordinates.MoveUp.apply(Red));

        //HOME
        Coordinates.MoveLeft.apply(Red);
        for (int i = 0; i < 5; i++)
            pathRed.put(T++, Coordinates.MoveLeft.apply(Red));
        pathRed.put(T++, Coordinates.MoveUp.apply(Red));
        for (int i = 0; i < 6; i++)
            pathRed.put(T++, Coordinates.MoveRight.apply(Red));

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

        public void jump(int ix, int iy) {
            x += ix / Math.abs(ix);
            y += iy / Math.abs(iy);
        }

    }

}