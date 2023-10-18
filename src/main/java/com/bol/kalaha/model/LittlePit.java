package com.bol.kalaha.model;

public class LittlePit extends Pit {
    private int numOfStones;

    public LittlePit(int id, int numberOfStones) {
        super(id);
        setNumOfStones(numberOfStones);
    }

    public int getNumOfStones() {
        return numOfStones;
    }

    public void setNumOfStones(int numOfStones) {
        this.numOfStones = numOfStones;
    }

    public boolean isEmpty() {
        return this.numOfStones == 0;
    }

    public void sow(int stoneCount) {
        this.numOfStones++;
    }
    public void startSow() {
        numOfStones = 0;
    }

    public boolean hasOpposite() {
        return true;
    }
}
