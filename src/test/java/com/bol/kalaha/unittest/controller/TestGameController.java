package com.bol.kalaha.unittest.controller;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.service.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGameController {

    @InjectMocks
    private GameController gameController;

    @Mock
    private Game game;

    @Test
    void should_callCreateOfGame_when_startGame() {

        gameController.startGame();

        verify(game, times(1)).create();
    }

    @Test
    void should_callMakeMoveOfGame_when_makeMove() {

        gameController.makeMove(1);

        verify(game, times(1)).makeMove(Mockito.anyInt());
    }

}