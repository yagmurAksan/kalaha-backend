package com.bol.kalaha.service;

import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.utils.Turn;
import org.springframework.stereotype.Service;

@Service
public class GameLogic {
    private final Sowing sowing;

    public Board board;

    public GameLogic(Sowing sowing){
        this.sowing = sowing;
    }

    public void setBoard(Board board) {
        this.board = board;
        sowing.setBoard(board);
    }

    public void executeSowing(Pit pit) {
        sowing.executeSowing(pit);
        Player player = Turn.getNextPlayer();
        board.setActivePlayer(player);
    }

    public Player determineWinner() {
        Player playerInTurn = Turn.getPlayerInTurn();
        Player playerOpponent = Turn.getPlayerOpponent();
        return playerInTurn.getBigPit().getNumOfStones() > playerOpponent.getBigPit().getNumOfStones() ? playerInTurn : playerOpponent;
    }
}
