package com.bol.kalaha.unittest.service;

import com.bol.kalaha.model.*;
import com.bol.kalaha.service.GameLogic;
import com.bol.kalaha.service.Sowing;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestGameLogic {

    @InjectMocks
    private GameLogic gameLogic;

    @Mock
    private Sowing sowing;

    private MockedStatic<Turn> turn;

    @BeforeEach
    public void beforeTest() {
        turn = mockStatic(Turn.class);
    }

    @AfterEach
    public void afterTest() {
        turn.close();
    }

    @Test
    public void should_setBoardOfSowing_when_setBoard(){
        Board board = new Board();
        gameLogic.setBoard(board);

        verify(sowing, Mockito.times(1)).setBoard(board);
    }

    @Test
    public void should_executeSowing_when_isStoneNotLeft(){
        Board board = new Board();
        gameLogic.setBoard(board);
        Player p1 = new Player(0, 0, 6);
        p1.setBigPit(new BigPit(0));
        LittlePit pit = new LittlePit(0, 1);
        ArrayList<Pit> littlePits = new ArrayList<>();
        littlePits.add(pit);
        p1.setLittlePits(littlePits);
        Player p2 = new Player(1, 7, 13);
        p2.setBigPit(new BigPit(1));
        p2.setLittlePits(new ArrayList<>());
        p1.setOpponent(p2);
        p2.setOpponent(p1);

        turn.when(Turn::getNextPlayer).thenReturn(p2);

        gameLogic.executeSowing(pit);

        verify(sowing, Mockito.times(1)).executeSowing(pit);
    }

    @Test
    public void should_returnPlayer1_when_determineWinner(){
        Player p1 = new Player(0, 0, 6);
        BigPit bigPit = new BigPit(6);
        bigPit.setNumOfStones(6);
        p1.setBigPit(bigPit);

        Player p2 = new Player(1, 7, 13);
        p2.setBigPit(new BigPit(13));

        p1.setOpponent(p2);
        p2.setOpponent(p1);

        turn.when(Turn::getPlayerInTurn).thenReturn(p1);
        turn.when(Turn::getPlayerOpponent).thenReturn(p2);

        Player actual = gameLogic.determineWinner();

        Assertions.assertEquals(p1, actual);
    }
}
