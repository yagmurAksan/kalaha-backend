package com.bol.kalaha.model;

import java.util.List;

public class Player {
    private int id;

    private final int minPitId;

    private final int maxPitId;

    private Player opponent;

    private List<Pit> littlePits;

    private BigPit bigPit;

    public Player(int id, int minPit, int maxPit) {
        this.id = id;
        this.minPitId = minPit;
        this.maxPitId = maxPit;
    }

    public int getId() {
        return id;
    }

    public Player getOpponent(){
        return this.opponent;
    }

    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    public List<Pit> getLittlePits() {
        return littlePits;
    }

    public void setLittlePits(List<Pit> littlePits) {
        this.littlePits = littlePits;
    }

    public BigPit getBigPit() {
        return bigPit;
    }

    public void setBigPit(BigPit bigPit) {
        this.bigPit = bigPit;
    }

    public boolean isPitOwned(Pit pit){
        return pit.getId() >= minPitId && pit.getId() <= maxPitId;
    }
}
