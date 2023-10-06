package com.game.ludo;

import java.util.Hashtable;

public class Piece {
    public String playerName;
    public LudoPlayer.Coordinates currentPosition;
    public Hashtable<Integer, LudoPlayer.Coordinates> positions;
    int stepCount;

    public Piece(Hashtable<Integer, LudoPlayer.Coordinates> Position, String playerName) {
        this.playerName = playerName;
        stepCount = 0;
        this.positions = Position;
        currentPosition = positions.get(stepCount);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCurrentPosition(LudoPlayer.Coordinates currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setPositions(Hashtable<Integer, LudoPlayer.Coordinates> positions) {
        this.positions = positions;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void updatePosition(int step) {
        setStepCount(stepCount + step);
        this.currentPosition = positions.get(stepCount);
    }

    @Override
    public String toString() {
        return  playerName + " @ " + stepCount ;
    }

    public boolean canmove(int step) {
        return step + stepCount <= 56;
    }

    public boolean reachedGoal() {
        return stepCount == 56;
    }
}