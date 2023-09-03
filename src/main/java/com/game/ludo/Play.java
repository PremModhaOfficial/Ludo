package com.game.ludo;
import java.util.ArrayList;
import java.util.Scanner;


public class Play {
    boolean gameFinished;
    static Scanner sc = new Scanner(System.in);
    String[] playernames = {
            "RED",
            "GREEN",
            "YELLOW",
            "BLUE"
    };
    public Play(int i) {
        players = new ArrayList<>();
        gameFinished = false;
        for (; i > 0 ; i--)
        players.add(new Player(playernames[i-1]));
    }

    public static void main(String[] args) {
        Play ludo = new Play(4);
        ludo.play();

    }

    protected void play() {
        while (!gameFinished) {
            for (Player current : players) {
                gameFinished = current.takeTurn();
                if (gameFinished) {
                    System.out.println(current.playerName + " Wins The Game");
                    return;
                }
                //todo remove this return
                return;
            }
        }
    }

    ArrayList<Player> players;
    class Player{
        ArrayList<Piece> pieces;
        ArrayList<Piece> available;
        String playerName;
        public Player(String s) {
            this.playerName = s;
            pieces = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                pieces.add(new Piece(i));
            }
        }

        public boolean takeTurn() {
            int step = rollDice();
            //todo//choice must always be valid!!
            Piece currentPiece = getChoiceOFPiece(pieces,step);
            if (currentPiece == null)
                return false;
            System.out.println("Player-" +this.playerName +" Piece-" +currentPiece.pieceNumber + " step-" + step);
            if ((currentPiece.distanceFromHome += step) == 56) {
                System.out.println("Piece "+currentPiece.pieceNumber+" has Reached the Goal");
                currentPiece.reachedGoal = true;
            }
            if (step==6)
                return takeTurn();
            return allPieceReachedGoal();
        }

        private boolean allPieceReachedGoal() {
            return pieces.get(0).reachedGoal
                    || pieces.get(1).reachedGoal
                    || pieces.get(2).reachedGoal
                    || pieces.get(3).reachedGoal;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "playerName='" + playerName + '\'' +
                    '}';
        }

        private Piece getChoiceOFPiece(ArrayList<Piece> pieces,int step) {
            //todo//scanner choice
            available = new ArrayList<>();
            for (int i = 0; i < pieces.size(); i++) {
                Piece curr = pieces.get(i);
                if ((curr.movable == false && step == 6) || (curr.canMakeWalkSteos(step) && curr.movable)) {
                    available.add(curr);
                    System.out.println("added");
                }
            }
                for (Piece p: available )
                    System.out.println(p.pieceNumber + " ");
                System.out.println("Enter Number");
//            int c = sc.nextInt();
//            while (c >= available.size()) {
//                System.out.println("Not available");
//                c = sc.nextInt();
//            }
//            return available.get(c);
            try {
                return available.get(0);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Not Applicable");
                return null;
            }
        }

        class Piece{

            public boolean reachedGoal;
            int pieceNumber;
            int distanceFromHome;
            double x,y;
            private double startX,startY;
            boolean  movable;

            public Piece(int pieceNumber) {
                this.pieceNumber = pieceNumber;
                movable = false;
                startX = 84.0;
                startY = 85.0;
                x = startX;
                y = startY;
            }

            public boolean canMakeWalkSteos(int step) {
                return distanceFromHome+step <= 56;
            }
        }
        private int rollDice(){
            return ((int) (Math.random() * 5.999999999) + 1);
        }
    }
}