package com.bol.kalaha.unittest.controller;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    void should_callCreateOfGameService_when_startGame() {

        gameController.startGame();

        verify(gameService, times(1)).create();
    }

    @Test
    void should_callexecuteSowingOfSowingService_when_makeMove() {
        Board board = new Board();
        board.setActivePlayer(new Player(1));

        when(gameService.getBoard()).thenReturn(board);

        gameController.makeMove(1);

        verify(sowingService, times(1)).executeSowing(Mockito.anyInt());
    }

}