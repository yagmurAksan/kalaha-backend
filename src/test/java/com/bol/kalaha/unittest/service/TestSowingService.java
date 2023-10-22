package com.bol.kalaha.unittest.service;

import com.bol.kalaha.model.*;
import com.bol.kalaha.service.SowingService;
import com.bol.kalaha.exception.SowingNotApplicableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TestSowingService {

    @InjectMocks
    private SowingService sowingService;

    private Board board;

    @BeforeEach
    public void beforeTest() {
        board = new Board();
        board.setPits(new ArrayList<>());
        Player playerActive = new Player(1);
        Player playerOpponent = new Player(2);

        LittlePit littlePit0Active = new LittlePit(0, 1);
        LittlePit littlePit1Active = new LittlePit(1, 0);
        LittlePit littlePit2Active = new LittlePit(2, 0);
        LittlePit littlePit3Active = new LittlePit(3, 0);
        LittlePit littlePit4Active = new LittlePit(4, 1);
        LittlePit littlePit5Active = new LittlePit(5, 0);
        BigPit bigPitActive = new BigPit(6);
        LittlePit littlePit7Opponent = new LittlePit(7, 1);
        LittlePit littlePit8Opponent = new LittlePit(8, 0);
        LittlePit littlePit9Opponent = new LittlePit(9, 0);
        LittlePit littlePit10Opponent = new LittlePit(10, 0);
        LittlePit littlePit11Opponent = new LittlePit(11, 0);
        LittlePit littlePit12Opponent = new LittlePit(12, 0);
        BigPit bigPitOpponent = new BigPit(12);

        littlePit0Active.setNext(littlePit1Active);
        littlePit1Active.setNext(littlePit2Active);
        littlePit2Active.setNext(littlePit3Active);
        littlePit3Active.setNext(littlePit4Active);
        littlePit4Active.setNext(littlePit5Active);
        littlePit5Active.setNext(bigPitActive);
        bigPitActive.setNext(littlePit7Opponent);
        littlePit7Opponent.setNext(littlePit8Opponent);
        littlePit8Opponent.setNext(littlePit9Opponent);
        littlePit9Opponent.setNext(littlePit10Opponent);
        littlePit10Opponent.setNext(littlePit11Opponent);
        littlePit11Opponent.setNext(littlePit12Opponent);
        littlePit12Opponent.setNext(bigPitOpponent);
        bigPitOpponent.setNext(littlePit0Active);

        ArrayList<Pit> littlePitsActive = new ArrayList<>();
        littlePitsActive.add(littlePit0Active);
        littlePitsActive.add(littlePit1Active);
        littlePitsActive.add(littlePit2Active);
        littlePitsActive.add(littlePit3Active);
        littlePitsActive.add(littlePit4Active);
        littlePitsActive.add(littlePit5Active);
        playerActive.setLittlePits(littlePitsActive);
        playerActive.setBigPit(bigPitActive);
        board.getPits().addAll(littlePitsActive);
        board.getPits().add(bigPitActive);

        ArrayList<Pit> littlePitsOpponent = new ArrayList<>();
        littlePitsOpponent.add(littlePit7Opponent);
        littlePitsOpponent.add(littlePit8Opponent);
        littlePitsOpponent.add(littlePit9Opponent);
        littlePitsOpponent.add(littlePit10Opponent);
        littlePitsOpponent.add(littlePit11Opponent);
        littlePitsOpponent.add(littlePit12Opponent);
        playerOpponent.setLittlePits(littlePitsOpponent);
        playerOpponent.setBigPit(bigPitOpponent);
        board.getPits().addAll(littlePitsOpponent);
        board.getPits().add(bigPitOpponent);

        playerActive.setOpponent(playerOpponent);
        playerOpponent.setOpponent(playerActive);
        board.setActivePlayer(playerActive);
        sowingService.setBoard(board);
    }

    private Pit getNextPit(Pit pit, int times) {
        Pit current = pit;
        for(int i=0; i<times; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Test
    public void should_throwException_when_executeSowingWithZeroStone(){
        Assertions.assertThrows(SowingNotApplicableException.class, () -> sowingService.executeSowing(1));
    }

    @Test
    public void should_throwException_when_executeSowingWithBigPit(){
        Assertions.assertThrows(SowingNotApplicableException.class, () -> sowingService.executeSowing(6));
    }

    @Test
    public void should_emptyPitAndAddOneToNextPit_when_executeSowing(){
        Pit littlePit = board.getActivePlayer().getOpponent().getLittlePits().get(0);

        sowingService.executeSowing(7);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(1, littlePit.getNext().getNumOfStones());
    }

    @Test
    public void should_notAddOneToOpponentBigPit_when_sowingOnBigPitOfOpponent(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(8);

        sowingService.executeSowing(5);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 2).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 3).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 4).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 5).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 6).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 7).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 8).getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 9).getNumOfStones());
    }

    @Test
    public void should_collectFromOpposite_when_executeSowingWithNextPitZero(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(4);

        sowingService.executeSowing(4);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 2).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 3).getNumOfStones());
    }

    @Test
    public void should_notCollectFromOpposite_when_executeSowingWithNextPitNotZero(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(4);
        Pit nextPit = board.getActivePlayer().getLittlePits().get(5);
        nextPit.setNumOfStones(1);

        sowingService.executeSowing(4);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 2).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 3).getNumOfStones());
    }

    @Test
    public void should_notCollectFromOpposite_when_executeSowingWithLastPitBigPit(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(1);

        sowingService.executeSowing(5);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 2).getNumOfStones());
    }

    @Test
    public void should_notCollectFromOpposite_when_executeSowingWithLastPitBelongsToOpponent(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(2);

        sowingService.executeSowing(5);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 2).getNumOfStones());
    }

    @Test
    public void should_notChangeTurn_when_executeSowingWithLastBigPitBelongsToActive(){
        Player activePlayer = board.getActivePlayer();
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(1);

        sowingService.executeSowing(5);

        Assertions.assertEquals(activePlayer, board.getActivePlayer());
    }

    @Test
    public void should_changeTurn_when_executeSowingWithLastPitBelongsToOpponent(){
        Player opponentPlayer = board.getActivePlayer().getOpponent();
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(2);

        sowingService.executeSowing(5);

        Assertions.assertEquals(opponentPlayer, board.getActivePlayer());
    }

    @Test
    public void should_setWinnerPlayer_when_stoneLeft(){
        Player activePlayer = board.getActivePlayer();
        Player opponentPlayer = board.getActivePlayer().getOpponent();
        activePlayer.getLittlePits().get(0).setNumOfStones(0);
        activePlayer.getLittlePits().get(4).setNumOfStones(0);
        Pit littlePit = activePlayer.getLittlePits().get(5);
        littlePit.setNumOfStones(2);

        sowingService.executeSowing(5);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(1, getNextPit(littlePit, 1).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 2).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 3).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 4).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 5).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 6).getNumOfStones());
        Assertions.assertEquals(0, getNextPit(littlePit, 7).getNumOfStones());
        Assertions.assertEquals(2, getNextPit(littlePit, 8).getNumOfStones());
        Assertions.assertEquals(opponentPlayer, board.getWinnerPlayer());
    }

    @Test
    public void should_notSetWinnerPlayer_when_stoneLeft(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(5);
        littlePit.setNumOfStones(2);

        sowingService.executeSowing(5);

        Assertions.assertEquals(null, board.getWinnerPlayer());
    }
}
