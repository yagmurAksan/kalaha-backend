package com.bol.kalaha.builder;

public class BoardCreator {
    public void createBoard(Builder builder){
        builder.reset();
        builder.setPlayer();
        builder.setPit();
    }
}
