package com.bol.kalaha.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BigPit extends Pit {

    public BigPit(int id) {
        super(id);
    }

    public boolean hasOpposite() {
        return false;
    }
}
