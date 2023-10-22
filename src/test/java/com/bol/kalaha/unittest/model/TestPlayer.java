package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.LittlePit;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer {

    private Player player;

    private Pit littlePit;

    @BeforeEach
    public void beforeTest() {
        player = new Player(0);
        littlePit = new LittlePit(0, 6);
        List<Pit> littlePits = new ArrayList<>();
        littlePits.add(littlePit);
        player.setLittlePits(littlePits);
    }

    @Test
    public void should_returnTrue_when_firstPlayerFirstPitId(){

        boolean actual = player.isPitOwned(littlePit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_secondPlayerFirstPitId(){
        Pit pit = new LittlePit(7, 6);

        boolean actual = player.isPitOwned(pit);

        Assertions.assertFalse(actual);
    }
}
