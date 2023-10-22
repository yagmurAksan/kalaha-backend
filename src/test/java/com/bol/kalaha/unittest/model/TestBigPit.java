package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.BigPit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestBigPit {

    private BigPit bigPit;

    @BeforeEach
    public void beforeTest() {
        bigPit = new BigPit(1);
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

        bigPit.sow();

        Assertions.assertEquals(1, bigPit.getNumOfStones());
    }

    @Test
    public void should_returnFalse_when_hasOpposite(){

        boolean actual = bigPit.hasOpposite();

        Assertions.assertFalse(actual);
    }
}
