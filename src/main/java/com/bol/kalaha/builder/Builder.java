package com.bol.kalaha.builder;

import com.bol.kalaha.model.Board;

public abstract class Builder {
    public abstract void reset();
    public abstract void setPlayer();
    public abstract void setPit();
    public abstract Board getBoard();
}
