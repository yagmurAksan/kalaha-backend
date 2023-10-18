package com.bol.kalaha.unittest.model;

import com.bol.kalaha.builder.BoardBuilder;
import com.bol.kalaha.builder.BoardCreator;
import com.bol.kalaha.config.Config;
import com.bol.kalaha.model.*;
import com.bol.kalaha.utils.PitNotFoundException;
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
        board.setActivePlayer(board.getPlayers().get(0));
    }

    @Test
    public void should_setActivePlayerToPlayer2_when_setActivePlayerAsPlayer2(){
        Player player1 = board.getPlayers().get(0);
        Player player2 = board.getPlayers().get(1);

        board.setActivePlayer(player2);

        Assertions.assertEquals(player2, board.getActivePlayer());
        Assertions.assertEquals(player1, board.getOpponentPlayer());
    }

    @Test
    public void should_returnTrue_when_littlePitWith1Stone(){
        LittlePit littlePit = new LittlePit(1, 1);

        boolean actual = board.checkIfAvailableToCollectOpposite(littlePit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_littlePitWith2Stone(){
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
        Pit bigPit = board.getPitById(Config.firstPlayerBigPitId);

        boolean actual = board.checkIfBelongActivePlayer(bigPit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_bigPitNotBelongsActivePlayer(){
        Pit bigPit = board.getPitById(Config.secondPlayerBigPitId);

        boolean actual = board.checkIfBelongActivePlayer(bigPit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_littlePitBelongsActivePlayer(){
        Pit littlePit = board.getPitById(Config.firstPlayerFirstPitId);

        boolean actual = board.checkIfBelongActivePlayer(littlePit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_littlePitNotBelongsActivePlayer(){
        Pit littlePit = board.getPitById(Config.secondPlayerFirstPitId);

        boolean actual = board.checkIfBelongActivePlayer(littlePit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_returnTrue_when_bigPitOfOpponentPlayer(){
        Pit bigPit = board.getPitById(Config.secondPlayerBigPitId);

        boolean actual = board.checkIfBigPitOfOpponentPlayer(bigPit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_bigPitOfActivePlayer(){
        Pit bigPit = board.getPitById(Config.firstPlayerBigPitId);

        boolean actual = board.checkIfBigPitOfOpponentPlayer(bigPit);

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_addBigPitWithOppositePitNumOfStones_when_collectFromOpposite(){
        Pit pit = board.getPitById(Config.firstPlayerFirstPitId);
        Pit oppositePit = board.getPitById(Config.secondPlayerFirstPitId);
        int expectedNumOfStones = board.getActivePlayer().getBigPit().getNumOfStones() + pit.getNumOfStones() + oppositePit.getNumOfStones();

        board.collectFromOpposite(pit);

        Assertions.assertEquals(expectedNumOfStones, board.getActivePlayer().getBigPit().getNumOfStones());
    }

    @Test
    public void should_notPutOpponentStonesToBigPit_when_activePlayerStoneLeft(){
        Player opponentPlayer = board.getOpponentPlayer();
        int expectedNumOfStones = opponentPlayer.getBigPit().getNumOfStones();

        board.postSowingBeforePlayerChange();

        Assertions.assertEquals(expectedNumOfStones, opponentPlayer.getBigPit().getNumOfStones());
    }

    @Test
    public void should_putOpponentStonesToBigPit_when_activePlayerStoneNotLeft(){
        board.getActivePlayer().getLittlePits().forEach(pit -> pit.setNumOfStones(0));
        Player opponentPlayer = board.getOpponentPlayer();
        int opponentNumOfStonesInLittlePits = opponentPlayer.getLittlePits().stream()
                .map(Pit::getNumOfStones)
                .reduce(0, Integer::sum);
        int expectedNumOfStones = opponentNumOfStonesInLittlePits + opponentPlayer.getBigPit().getNumOfStones();

        board.postSowingBeforePlayerChange();

        Assertions.assertEquals(expectedNumOfStones, opponentPlayer.getBigPit().getNumOfStones());
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
    public void should_returnPit_when_getPitById0(){
        Pit expectedPit = board.getActivePlayer().getLittlePits().get(0);

        Pit pit = board.getPitById(0);

        Assertions.assertEquals(expectedPit, pit);
    }

    @Test
    public void should_throwException_when_getPitById16(){
        int pitId = 16;

        Assertions.assertThrows(PitNotFoundException.class, () -> board.getPitById(pitId));
    }

    @Test
    public void should_notThrowException_when_getPitById0(){
        int pitId = 0;

        Assertions.assertDoesNotThrow(() -> board.getPitById(pitId));
    }
}
