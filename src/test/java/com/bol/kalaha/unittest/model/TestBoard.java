package com.bol.kalaha.unittest.model;

import com.bol.kalaha.builder.BoardBuilder;
import com.bol.kalaha.builder.BoardCreator;
import com.bol.kalaha.constant.GameConstants;
import com.bol.kalaha.model.*;
import com.bol.kalaha.exception.PitNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBoard {
    private Board board;

    @BeforeEach
    public void beforeTest() {
        BoardCreator boardCreator = new BoardCreator();
        BoardBuilder boardBuilder = new BoardBuilder();
        boardCreator.createBoard(boardBuilder);

        board = boardBuilder.getBoard();
    }

    @Test
    public void should_returnTrue_when_littlePitWithOneStone(){
        LittlePit littlePit = new LittlePit(1, 1);

        boolean actual = board.checkIfAvailableToCollectOpposite(littlePit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_littlePitWithNotOneStone(){
        LittlePit littlePit = new LittlePit(1, 2);

        boolean actual = board.checkIfAvailableToCollectOpposite(littlePit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnFalse_when_bigPit(){
        BigPit bigPit = new BigPit(1);

        boolean actual = board.checkIfAvailableToCollectOpposite(bigPit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_bigPitBelongsActivePlayer(){
        Pit bigPit = board.getPitById(GameConstants.firstPlayerBigPitId);

        boolean actual = board.checkIfBelongActivePlayer(bigPit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_bigPitNotBelongsActivePlayer(){
        Pit bigPit = board.getPitById(GameConstants.secondPlayerBigPitId);

        boolean actual = board.checkIfBelongActivePlayer(bigPit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_littlePitBelongsActivePlayer(){
        Pit littlePit = board.getPitById(GameConstants.firstPlayerFirstPitId);

        boolean actual = board.checkIfBelongActivePlayer(littlePit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_littlePitNotBelongsActivePlayer(){
        Pit littlePit = board.getPitById(GameConstants.secondPlayerFirstPitId);

        boolean actual = board.checkIfBelongActivePlayer(littlePit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_bigPitOfOpponentPlayer(){
        Pit bigPit = board.getPitById(GameConstants.secondPlayerBigPitId);

        boolean actual = board.checkIfBigPitOfOpponentPlayer(bigPit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_bigPitOfActivePlayer(){
        Pit bigPit = board.getPitById(GameConstants.firstPlayerBigPitId);

        boolean actual = board.checkIfBigPitOfOpponentPlayer(bigPit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_activePlayerStoneLeft(){

        boolean actual = board.checkIfActivePlayerStoneLeft();

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_activePlayerStoneNotLeft(){
        board.getActivePlayer().getLittlePits().forEach(pit -> pit.setNumOfStones(0));

        boolean actual = board.checkIfActivePlayerStoneLeft();

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_addNumOfStonesOfOppositePitToBigPit_when_collectFromOpposite(){
        Pit pit = board.getPitById(GameConstants.firstPlayerFirstPitId);
        Pit oppositePit = board.getPitById(GameConstants.secondPlayerBigPitId-1);
        int expectedNumOfStones = board.getActivePlayer().getBigPit().getNumOfStones() + pit.getNumOfStones() + oppositePit.getNumOfStones();

        board.collectFromOpposite(pit);

        Assertions.assertEquals(expectedNumOfStones, board.getActivePlayer().getBigPit().getNumOfStones());
    }

    @Test
    public void should_notPutOpponentStonesToBigPit_when_activePlayerStoneLeft(){
        Player opponentPlayer = board.getActivePlayer().getOpponent();
        int expectedNumOfStones = opponentPlayer.getBigPit().getNumOfStones();

        board.postSowing();

        Assertions.assertEquals(expectedNumOfStones, opponentPlayer.getBigPit().getNumOfStones());
    }

    @Test
    public void should_putOpponentStonesToBigPit_when_activePlayerStoneNotLeft(){
        board.getActivePlayer().getLittlePits().forEach(pit -> pit.setNumOfStones(0));
        Player opponentPlayer = board.getActivePlayer().getOpponent();
        int opponentNumOfStonesInLittlePits = opponentPlayer.getLittlePits().stream()
                .map(Pit::getNumOfStones)
                .reduce(0, Integer::sum);
        int expectedNumOfStones = opponentNumOfStonesInLittlePits + opponentPlayer.getBigPit().getNumOfStones();

        board.postSowing();

        Assertions.assertEquals(expectedNumOfStones, opponentPlayer.getBigPit().getNumOfStones());
        opponentPlayer.getLittlePits().forEach(pit -> Assertions.assertEquals(0, pit.getNumOfStones()));
    }

    @Test
    public void should_returnFirstPit_when_getPitByIdZero(){
        Pit expectedPit = board.getActivePlayer().getLittlePits().get(0);

        Pit pit = board.getPitById(0);

        Assertions.assertEquals(expectedPit, pit);
    }

    @Test
    public void should_throwException_when_getPitByInvalidId(){
        int pitId = 16;

        Assertions.assertThrows(PitNotFoundException.class, () -> board.getPitById(pitId));
    }

    @Test
    public void should_notThrowException_when_getPitByValidId(){
        int pitId = 0;

        Assertions.assertDoesNotThrow(() -> board.getPitById(pitId));
    }
}
