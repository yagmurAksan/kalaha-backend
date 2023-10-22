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
        return numOfStones == 0;
    }

    public void sow() {
        numOfStones++;
    }

    public abstract boolean hasOpposite();

}
