package com.bol.kalaha.service;

import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import org.springframework.stereotype.Service;

@Service
public class Sowing {
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }
    public void executeSowing(Pit pit){
        int initialStoneCount = pit.getNumOfStones();
        pit.startSow();
        var lastSowedPit = sowSubsequent(pit, initialStoneCount);
        finishSowing(lastSowedPit);
        postSow();
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

    private void finishSowing(Pit currentPit){
        if(board.checkIfAvailableToCollectOpposite(currentPit)) {
            if(board.checkIfBelongActivePlayer(currentPit)) {
                board.collectFromOpposite(currentPit);
                currentPit.setNumOfStones(0);
            }
        }
    }

    private void postSow(){
        board.postSowingBeforePlayerChange();
    }
}
