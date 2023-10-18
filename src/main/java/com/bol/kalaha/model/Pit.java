package com.bol.kalaha.model;

public abstract class Pit {

    private int id;

    private Player player;

    private Pit next;

    public Pit(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Pit getNext() {
        return next;
    }

    public void setNext(Pit next) {
        this.next = next;
    }

    public abstract int getNumOfStones();

    public abstract void setNumOfStones(int numOfStones);

    public abstract boolean isEmpty();

    public abstract void startSow();

    public abstract void sow(int stoneCount);

    public abstract boolean hasOpposite();

}
