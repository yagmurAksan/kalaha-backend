package com.bol.kalaha.service;

import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.exception.SowingNotApplicableException;
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
        startSowing(pit);
        var lastSowedPit = sowSubsequent(pit, initialStoneCount);
        finishSowing(lastSowedPit);
        determineTurn(lastSowedPit);
        determineWinner();
    }

    private static void startSowing(Pit pit) {
        if(pit.hasOpposite()) {
            pit.setNumOfStones(0);
        } else {
            throw new SowingNotApplicableException("Sowing cannot be started by big pit.");
        }
    }

    private Pit sowSubsequent(Pit pit, int stoneCount) {
        while(stoneCount > 0) {
            pit = pit.getNext();
            if(!board.checkIfBigPitOfOpponentPlayer(pit)) {
                pit.sow();
                stoneCount--;
            }
        }
        return pit;
    }

    private void finishSowing(Pit lastSowedPit){
        if(board.checkIfBelongActivePlayer(lastSowedPit) && board.checkIfAvailableToCollectOpposite(lastSowedPit)) {
            board.collectFromOpposite(lastSowedPit);
            lastSowedPit.setNumOfStones(0);
        }
        board.postSowing();
    }

    private void determineTurn(Pit lastSowedPit) {
        if(!board.checkIfBelongActivePlayer(lastSowedPit) || lastSowedPit.hasOpposite()) {
            board.setActivePlayer(board.getActivePlayer().getOpponent());
        }
    }

    private void determineWinner() {
        if(!board.isStoneLeft()) {
            Player playerActive = board.getActivePlayer();
            Player playerOpponent = playerActive.getOpponent();
            Player winnerPlayer = playerActive.getBigPit().getNumOfStones() > playerOpponent.getBigPit().getNumOfStones() ? playerActive : playerOpponent;
            board.setWinnerPlayer(winnerPlayer);
        }
    }
}
