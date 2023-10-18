package com.bol.kalaha.model;

import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;

public class BigPit extends Pit {
    private int numOfStones;
    public BigPit(int id) {
        super(id);
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
        if(stoneCount==1) {
            Turn.turnEndedOnBigPit();
        }
    }
    public void startSow() {
        throw new SowingNotApplicableException("Sowing cannot be started by big pit.");
    }

    public boolean hasOpposite() {
        return false;
    }
}
