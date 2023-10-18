package com.bol.kalaha.model;

import com.bol.kalaha.config.Config;
import com.bol.kalaha.utils.PitNotFoundException;

import java.util.List;

public class Board {
    private List<Player> players;
    private Player activePlayer;
    private Player opponentPlayer;
    private List<Pit> pits;
    private boolean isStoneLeft;

    public Board() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;
        this.opponentPlayer = this.activePlayer.getOpponent();
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public List<Pit> getPits() {
        return pits;
    }

    public void setPits(List<Pit> pits) {
        this.pits = pits;
    }

    public boolean isStoneLeft() {
        return isStoneLeft;
    }

    public boolean checkIfAvailableToCollectOpposite(Pit pit) {
        return pit.hasOpposite() && pit.getNumOfStones() == 1;
    }

    public boolean checkIfBelongActivePlayer(Pit pit) {
        return activePlayer.isPitOwned(pit);
    }

    private void putOpponentStonesToBigPit() {
        int sumOfStonesInLittlePits = opponentPlayer.getLittlePits().stream()
                .map(Pit::getNumOfStones)
                .reduce(0, Integer::sum);
        opponentPlayer.getLittlePits().forEach(pit -> pit.setNumOfStones(0));
        opponentPlayer.getBigPit().setNumOfStones(opponentPlayer.getBigPit().getNumOfStones() + sumOfStonesInLittlePits);
    }

    public void collectFromOpposite(Pit pit) {
        var oppositePit = getPitById(Math.abs(pit.getId() - Config.totalLittlePitCount));
        activePlayer.getBigPit().setNumOfStones(pit.getNumOfStones() + oppositePit.getNumOfStones() + activePlayer.getBigPit().getNumOfStones());
        oppositePit.setNumOfStones(0);
    }

    public void postSowingBeforePlayerChange() {
        if(!checkIfActivePlayerStoneLeft()){
            putOpponentStonesToBigPit();
        }
    }

    public boolean checkIfActivePlayerStoneLeft(){
        for(Pit pit : activePlayer.getLittlePits()) {
            if(pit.getNumOfStones() > 0) {
                return isStoneLeft = true;
            }
        }
        return isStoneLeft = false;
    }

    public boolean checkIfBigPitOfOpponentPlayer(Pit pit) {
        return opponentPlayer.getBigPit().equals(pit);
    }

    public Pit getPitById(int id) {
        return pits.stream()
                .filter(pit -> pit.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PitNotFoundException("Pit with ID: " + id + " is not found"));
    }
}
