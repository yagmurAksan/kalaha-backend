package com.bol.kalaha.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Pit {

    private int id;

    private Pit next;

    private int numOfStones;

    public Pit(int id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return getNumOfStones() == 0;
    }

    public abstract void startSowing();

    public abstract void sow(int stoneCount);

    public abstract boolean hasOpposite();

}
