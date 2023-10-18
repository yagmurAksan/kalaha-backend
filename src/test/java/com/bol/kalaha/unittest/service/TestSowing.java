package com.bol.kalaha.unittest.service;

import com.bol.kalaha.model.*;
import com.bol.kalaha.service.Sowing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TestSowing {

    @InjectMocks
    private Sowing sowing;

    private Board board;

    @BeforeEach
    public void beforeTest() {
        board = new Board();
        Player player = new Player(0, 0, 6);
        Player playerOpponent = new Player(0, 0, 6);

        ArrayList<Pit> littlePits = new ArrayList<>();
        LittlePit littlePit = new LittlePit(0, 1);
        LittlePit littlePitNext = new LittlePit(1, 6);
        littlePit.setNext(littlePitNext);
        littlePits.add(littlePit);
        littlePits.add(littlePitNext);

        player.setLittlePits(littlePits);
        player.setBigPit(new BigPit(0));
        playerOpponent.setLittlePits(new ArrayList<>());
        playerOpponent.setBigPit(new BigPit(0));

        player.setOpponent(playerOpponent);
        board.setActivePlayer(player);
        sowing.setBoard(board);
    }

    @Test
    public void should_emptyNumOfStonesAddNextNumOfStones_when_executeSowing(){
        Pit littlePit = board.getActivePlayer().getLittlePits().get(0);

        sowing.executeSowing(littlePit);

        Assertions.assertEquals(0, littlePit.getNumOfStones());
        Assertions.assertEquals(7, littlePit.getNext().getNumOfStones());
    }
}
