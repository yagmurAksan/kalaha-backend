package com.bol.kalaha.model;

import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BigPit extends Pit {

    public BigPit(int id) {
        super(id);
    }

    public void sow(int stoneCount) {
        setNumOfStones(getNumOfStones() + 1);
        if(stoneCount==1) {
            Turn.turnEndedOnBigPit();
        }
    }

    public void startSowing() {
        throw new SowingNotApplicableException("Sowing cannot be started by big pit.");
    }

    public boolean hasOpposite() {
        return false;
    }
}
