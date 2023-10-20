package com.bol.kalaha.unittest.utils;

import com.bol.kalaha.model.Player;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTurn {


    @Test
    public void should_setWillChangeToFalse_when_turnEndedOnBigPit(){

        Turn.turnEndedOnBigPit();

        Assertions.assertFalse(Turn.isWillChange());
    }

    @Test
    public void should_returnPlayerInTurn_when_willChangeFalse(){
        Player p1 = new Player(0);
        Turn.setPlayerInTurn(p1);

        Turn.turnEndedOnBigPit();
        Player actual = Turn.getNextPlayer();

        Assertions.assertEquals(p1, actual);
        Assertions.assertTrue(Turn.isWillChange());
    }

    @Test
    public void should_returnPlayerOpponent_when_willChangeTrue(){
        Player p1 = new Player(0);
        Player p2 = new Player(1);
        p1.setOpponent(p2);
        p2.setOpponent(p1);
        Turn.setPlayerInTurn(p1);
        Turn.setPlayerOpponent(p2);

        Player actual = Turn.getNextPlayer();

        Assertions.assertEquals(p2, actual);
        Assertions.assertEquals(p2, Turn.getPlayerInTurn());
        Assertions.assertEquals(p1, Turn.getPlayerOpponent());
        Assertions.assertTrue(Turn.isWillChange());
    }
}
