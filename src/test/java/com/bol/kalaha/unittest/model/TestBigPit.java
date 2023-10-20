package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.BigPit;
import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class TestBigPit {

    private BigPit bigPit;

    private MockedStatic<Turn> turn;

    @BeforeEach
    public void beforeTest() {
        bigPit = new BigPit(1);
        turn = mockStatic(Turn.class);
    }

    @AfterEach
    public void afterTest() {
        turn.close();
    }

    @Test
    public void should_returnTrue_when_numOfStonesZero(){
        bigPit.setNumOfStones(0);

        boolean actual = bigPit.isEmpty();

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_numOfStonesGreaterThanZero(){
        bigPit.setNumOfStones(1);

        boolean actual = bigPit.isEmpty();

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_incrementStones_when_sow(){
        int stoneCount = 2;

        bigPit.sow(stoneCount);

        Assertions.assertEquals(1, bigPit.getNumOfStones());
    }

    @Test
    public void should_notCallTurnEndedOnBigPit_when_sowWithMoreThanOneStone(){
        int stoneCount = 2;

        bigPit.sow(stoneCount);

        turn.verify(Turn::turnEndedOnBigPit, never());
    }

    @Test
    public void should_callTurnEndedOnBigPit_when_sowWithOneStone(){
        int stoneCount = 1;

        bigPit.sow(stoneCount);

        turn.verify(Turn::turnEndedOnBigPit, only());
    }

    @Test
    public void should_throwException_when_startSowing(){
        Assertions.assertThrows(SowingNotApplicableException.class, () -> bigPit.startSowing());
    }

    @Test
    public void should_returnFalse_when_hasOpposite(){

        boolean actual = bigPit.hasOpposite();

        Assertions.assertFalse(actual);
    }
}
