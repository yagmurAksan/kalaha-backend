package com.bol.kalaha.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.bol.kalaha.config.Config.*;

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
        int minPitId = id==firstPlayerId ? firstPlayerFirstPitId : secondPlayerFirstPitId;
        int maxPitId = id==firstPlayerId ? firstPlayerBigPitId : secondPlayerBigPitId;
        return pit.getId() >= minPitId && pit.getId() <= maxPitId;
    }
}
