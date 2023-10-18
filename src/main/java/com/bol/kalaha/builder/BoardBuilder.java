package com.bol.kalaha.builder;

import com.bol.kalaha.utils.Turn;
import com.bol.kalaha.config.Config;
import com.bol.kalaha.model.*;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder extends Builder{
    private Board board;
    public void reset(){
        board = new Board();
    }
    public void setPlayer(){
        List<Player> players = new ArrayList<>();
        Player p1 = new Player(Config.firstPlayerId, 0, 6);
        Player p2 = new Player(Config.secondPlayerId, 7, 13);
        p1.setOpponent(p2);
        p2.setOpponent(p1);
        players.add(p1);
        players.add(p2);
        Turn.setPlayerInTurn(p1);
        Turn.setPlayerOpponent(p2);
        board.setActivePlayer(p1);
        board.setPlayers(players);
    }
    public void setPit(){
        List<Pit> pits = new ArrayList<>();
        setPit(pits);
        setNextPit(pits);
    }
    private void setPit(List<Pit> pits){
        fillPitList(pits,Config.firstPlayerFirstPitId);
        BigPit bigPitFirstPlayer = new BigPit(Config.firstPlayerBigPitId);
        pits.add(bigPitFirstPlayer);

        fillPitList(pits,Config.secondPlayerFirstPitId);
        BigPit bigPitSecondPlayer = new BigPit(Config.secondPlayerBigPitId);
        pits.add(bigPitSecondPlayer);

        board.getPlayers().get(0).setBigPit(bigPitFirstPlayer);
        board.getPlayers().get(0).setLittlePits(pits.subList(0,6));
        board.getPlayers().get(1).setBigPit(bigPitSecondPlayer);
        board.getPlayers().get(1).setLittlePits(pits.subList(7,13));
        board.setPits(pits);
    }
    private void fillPitList(List<Pit> pits, int id){
        for(int i = id; i<id+Config.littlePitCountPerPlayer; i++){
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
