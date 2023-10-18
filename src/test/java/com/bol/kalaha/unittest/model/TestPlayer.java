package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.LittlePit;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {

    private Player player;

    @BeforeEach
    public void beforeTest() {
        player = new Player(0, 0, 6);
    }

    @Test
    public void should_returnTrue_when_pitId2(){
        Pit pit = new LittlePit(2, 6);

        boolean actual = player.isPitOwned(pit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_pitId10(){
        Pit pit = new LittlePit(10, 6);

        boolean actual = player.isPitOwned(pit);

        Assertions.assertFalse(actual);
    }
}
