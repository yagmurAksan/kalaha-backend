package com.bol.kalaha.unittest.service;

import com.bol.kalaha.service.Game;
import com.bol.kalaha.model.Pit;
import com.bol.kalaha.service.GameLogic;
import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestGame {

    @InjectMocks
    private Game game;

    @Mock
    private GameLogic gameLogic;

    @BeforeEach
    public void beforeTest() {
        game.create();
    }

    @Test
    public void should_createGame_when_create(){

        Assertions.assertNotNull(game);
        Assertions.assertNotNull(game.getBoard());
        Assertions.assertNotNull(game.getBoard().getPlayers());
        Assertions.assertNotNull(game.getBoard().getPits());
        Assertions.assertNotNull(Turn.getPlayerInTurn());
        Assertions.assertNotNull(Turn.getPlayerOpponent());
    }

    @Test
    public void should_startSowing_when_makeMove(){
        Pit pit = game.getBoard().getPits().get(0);
        game.makeMove(0);

        verify(gameLogic, times(1)).executeSowing(pit);
    }

    @Test
    public void should_throwException_when_makeMoveWith0Stone(){
        game.getBoard().getPits().get(0).setNumOfStones(0);

        Assertions.assertThrows(SowingNotApplicableException.class, () -> game.makeMove(0));
    }
}
