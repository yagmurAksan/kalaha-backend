package com.bol.kalaha.builder;

import com.bol.kalaha.constant.GameConstants;
import com.bol.kalaha.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.bol.kalaha.constant.GameConstants.*;

public class BoardBuilder extends Builder{
    private Board board;
    public void reset(){
        board = new Board();
    }
    public void setPlayer(){
        Player p1 = new Player(GameConstants.firstPlayerId);
        Player p2 = new Player(GameConstants.secondPlayerId);
        p1.setOpponent(p2);
        p2.setOpponent(p1);
        board.setActivePlayer(p1);
    }
    public void setPit(){
        List<Pit> pits = new ArrayList<>();
        setPit(pits);
        setNextPit(pits);
    }
    private void setPit(List<Pit> pits){
        fillPitListWithLittlePits(pits, firstPlayerFirstPitId);
        BigPit bigPitFirstPlayer = new BigPit(firstPlayerBigPitId);
        pits.add(bigPitFirstPlayer);

        fillPitListWithLittlePits(pits, secondPlayerFirstPitId);
        BigPit bigPitSecondPlayer = new BigPit(secondPlayerBigPitId);
        pits.add(bigPitSecondPlayer);

        board.getActivePlayer().setBigPit(bigPitFirstPlayer);
        board.getActivePlayer().setLittlePits(pits.subList(firstPlayerFirstPitId, firstPlayerBigPitId));
        board.getActivePlayer().getOpponent().setBigPit(bigPitSecondPlayer);
        board.getActivePlayer().getOpponent().setLittlePits(pits.subList(secondPlayerFirstPitId, secondPlayerBigPitId));
        board.setPits(pits);
    }
    private void fillPitListWithLittlePits(List<Pit> pits, int id){
        for(int i = id; i<id+ littlePitCountPerPlayer; i++){
            pits.add(new LittlePit(i, 6));
        }
    }

    private void setNextPit(List<Pit> pits){
        var prevPit = pits.get(0);

        for(int i = 1; i<pits.size(); i++) {
            Pit currPit = pits.get(i);
            prevPit.setNext(currPit);
            prevPit = currPit;
        }

        prevPit.setNext(pits.get(0));
    }

    public Board getBoard(){
        return board;
    }
}
