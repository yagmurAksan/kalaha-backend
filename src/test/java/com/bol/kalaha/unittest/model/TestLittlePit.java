package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.LittlePit;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class TestLittlePit {

    private LittlePit littlePit;

    private MockedStatic<Turn> turn;

    @BeforeEach
    public void beforeTest() {
        littlePit = new LittlePit(1, 6);
        turn = mockStatic(Turn.class);
    }

    @AfterEach
    public void afterTest() {
        turn.close();
    }

    @Test
    public void should_returnTrue_when_noNumOfStones(){
        littlePit.setNumOfStones(0);

        boolean actual = littlePit.isEmpty();

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnTrue_when_numOfStones(){
        littlePit.setNumOfStones(1);

        boolean actual = littlePit.isEmpty();

        Assertions.assertFalse(actual);
    }

    @Test
    public void should_incNumOfStones_when_anyStoneCount(){
        int stoneCount = 2;

        littlePit.sow(stoneCount);

        Assertions.assertEquals(7, littlePit.getNumOfStones());
    }

    @Test
    public void should_makeNumOfStonesZero_when_startSow(){

        littlePit.startSow();

        Assertions.assertEquals(0, littlePit.getNumOfStones());
    }

    @Test
    public void should_returnFalse_when_hasOpposite(){

        boolean actual = littlePit.hasOpposite();

        Assertions.assertTrue(actual);
    }
}
