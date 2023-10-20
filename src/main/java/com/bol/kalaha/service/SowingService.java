package com.bol.kalaha.service;

import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SowingService {
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void executeSowing(int pitId){
        Pit pit = board.getPitById(pitId);
        if(pit.isEmpty()) {
            throw new SowingNotApplicableException("Sowing cannot be started with 0 stone.");
        }
        int initialStoneCount = pit.getNumOfStones();
        pit.startSowing();
        var lastSowedPit = sowSubsequent(pit, initialStoneCount);
        finishSowing(lastSowedPit);
        finishTurn();
    }

    private Pit sowSubsequent(Pit pit, int stoneCount) {
        while(stoneCount > 0) {
            pit = pit.getNext();
            if(!board.checkIfBigPitOfOpponentPlayer(pit)) {
                pit.sow(stoneCount);
                stoneCount--;
            }
        }
        return pit;
    }

    private void finishSowing(Pit lastSowedPit){
        if(board.checkIfAvailableToCollectOpposite(lastSowedPit) && board.checkIfBelongActivePlayer(lastSowedPit)) {
            board.collectFromOpposite(lastSowedPit);
            lastSowedPit.setNumOfStones(0);
        }
    }

    private void finishTurn(){
        board.postSowing();
        determineTurn();
        if(!board.isStoneLeft()) {
            determineWinner();
        }
    }

    private void determineTurn() {
        Player player = Turn.getNextPlayer();
        board.setActivePlayer(player);
    }

    private void determineWinner() {
        Player playerInTurn = Turn.getPlayerInTurn();
        Player playerOpponent = Turn.getPlayerOpponent();
        Player winnerPlayer = playerInTurn.getBigPit().getNumOfStones() > playerOpponent.getBigPit().getNumOfStones() ? playerInTurn : playerOpponent;
        board.setWinnerPlayer(winnerPlayer);
    }
}
