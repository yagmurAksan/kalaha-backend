package com.bol.kalaha.model;

import com.bol.kalaha.constant.GameConstants;
import com.bol.kalaha.exception.PitNotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    private Player activePlayer;
    private Player winnerPlayer;
    private List<Pit> pits;
    private boolean isStoneLeft;

    public boolean checkIfAvailableToCollectOpposite(Pit pit) {
        return pit.hasOpposite() && pit.getNumOfStones() == 1;
    }

    public boolean checkIfBelongActivePlayer(Pit pit) {
        return activePlayer.isPitOwned(pit);
    }

    public boolean checkIfBigPitOfOpponentPlayer(Pit pit) {
        return getActivePlayer().getOpponent().getBigPit().equals(pit);
    }

    public boolean checkIfActivePlayerStoneLeft(){
        return isStoneLeft = activePlayer.getLittlePits().stream().anyMatch(pit -> pit.getNumOfStones() > 0);
    }

    public void collectFromOpposite(Pit pit) {
        var oppositePit = getPitById(Math.abs(pit.getId() - GameConstants.totalLittlePitCount));
        activePlayer.getBigPit().setNumOfStones(pit.getNumOfStones() + oppositePit.getNumOfStones() + activePlayer.getBigPit().getNumOfStones());
        oppositePit.setNumOfStones(0);
    }

    public void postSowing() {
        if(!checkIfActivePlayerStoneLeft()){
            putOpponentStonesToBigPit();
        }
    }

    private void putOpponentStonesToBigPit() {
        Player opponentPlayer = activePlayer.getOpponent();
        int sumOfStonesInLittlePits = opponentPlayer.getLittlePits().stream()
                .map(Pit::getNumOfStones)
                .reduce(0, Integer::sum);
        opponentPlayer.getLittlePits().forEach(pit -> pit.setNumOfStones(0));
        opponentPlayer.getBigPit().setNumOfStones(opponentPlayer.getBigPit().getNumOfStones() + sumOfStonesInLittlePits);
    }

    public Pit getPitById(int id) {
        return pits.stream()
                .filter(pit -> pit.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PitNotFoundException("Pit with ID: " + id + " is not found"));
    }
}
