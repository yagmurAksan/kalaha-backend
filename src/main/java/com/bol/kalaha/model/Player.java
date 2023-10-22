package com.bol.kalaha.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {
    private int id;

    private Player opponent;

    private List<Pit> littlePits;

    private BigPit bigPit;

    public Player(int id) {
        this.id = id;
    }

    public boolean isPitOwned(Pit pit){
        return littlePits.contains(pit) || bigPit == pit;
    }
}
