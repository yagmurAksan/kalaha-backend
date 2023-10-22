package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.LittlePit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLittlePit {

    private LittlePit littlePit;

    @BeforeEach
    public void beforeTest() {
        littlePit = new LittlePit(1, 6);
    }

    @Test
    public void should_returnTrue_when_numOfStonesZero(){
        littlePit.setNumOfStones(0);

        boolean actual = littlePit.isEmpty();

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_numOfStonesGreaterThanZero(){
        littlePit.setNumOfStones(1);

        boolean actual = littlePit.isEmpty();

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_incrementStones_when_sow(){

        littlePit.sow();

        Assertions.assertEquals(7, littlePit.getNumOfStones());
    }

    @Test
    public void should_returnTrue_when_hasOpposite(){

        boolean actual = littlePit.hasOpposite();

        Assertions.assertTrue(actual);
    }
}
