package com.bol.kalaha.unittest.controller;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGameControllerService {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;

    @Mock
    private SowingService sowingService;

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
    void should_callCreateOfGameService_when_startGame() {

        gameController.startGame();

        verify(gameService, times(1)).create();
    }

    @Test
    void should_callexecuteSowingOfSowingService_when_makeMove() {

        turn.when(Turn::getPlayerInTurn).thenReturn(new Player(0));

        gameController.makeMove(1);

        verify(sowingService, times(1)).executeSowing(Mockito.anyInt());
    }

}