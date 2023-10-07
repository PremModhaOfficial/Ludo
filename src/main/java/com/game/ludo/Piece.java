package com.game.ludo;

import java.util.Hashtable;

public class Piece {
    public String playerName;
    public LudoPlayer.Coordinates currentPosition;
    public Hashtable<Integer, LudoPlayer.Coordinates> pathTable;
    int stepCount;

    public Piece(Hashtable<Integer, LudoPlayer.Coordinates> pathTable, String playerName) {
        this.playerName = playerName;
        stepCount = -1;
        this.pathTable = pathTable;
        currentPosition = this.pathTable.get(stepCount);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCurrentPosition(LudoPlayer.Coordinates currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setPathTable(Hashtable<Integer, LudoPlayer.Coordinates> pathTable) {
        this.pathTable = pathTable;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void updatePosition(int step) {
        LudoPlayer.Coordinates previousPosition = currentPosition;
        if (stepCount == -1) {
            setStepCount(0);
        } else {
            setStepCount(stepCount + step);
        }
        this.currentPosition = pathTable.get(stepCount);
    }

    @Override
    public String toString() {
        return  playerName + " @ " + stepCount ;
    }

    public boolean canMove(int step) {
        if (stepCount == -1 && step==6) {
            return true;
        } else if (step < 6 && stepCount == -1) {
            return false;
        } else {
            return step + stepCount <= 56;
        }
    }

    public boolean reachedGoal() {
        return stepCount == 56;
    }
}