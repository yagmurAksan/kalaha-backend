package com.bol.kalaha.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LittlePit extends Pit {

    public LittlePit(int id, int numberOfStones) {
        super(id);
        setNumOfStones(numberOfStones);
    }

    public boolean hasOpposite() {
        return true;
    }
}
