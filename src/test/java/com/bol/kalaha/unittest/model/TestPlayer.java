package com.bol.kalaha.unittest.model;

import com.bol.kalaha.model.LittlePit;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bol.kalaha.config.Config.*;

public class TestPlayer {

    private Player player;

    @BeforeEach
    public void beforeTest() {
        player = new Player(firstPlayerId);
    }

    @Test
    public void should_returnTrue_when_firstPlayerFirstPitId(){
        Pit pit = new LittlePit(firstPlayerFirstPitId, 6);

        boolean actual = player.isPitOwned(pit);

        Assertions.assertTrue(actual);
    }

    @Test
    public void should_returnFalse_when_secondPlayerFirstPitId(){
        Pit pit = new LittlePit(secondPlayerFirstPitId, 6);

        boolean actual = player.isPitOwned(pit);

        Assertions.assertFalse(actual);
    }
}
