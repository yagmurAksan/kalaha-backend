package com.bol.kalaha.unittest.service;

import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestGameService {

    @InjectMocks
    private GameService gameService;

    @Mock
    private SowingService sowingService;

    @BeforeEach
    public void beforeTest() {
        gameService.create();
    }

    @Test
    public void should_createGame_when_create(){
        Assertions.assertNotNull(gameService);
        Assertions.assertNotNull(gameService.getBoard());
        Assertions.assertNotNull(gameService.getBoard().getActivePlayer());
        Assertions.assertNull(gameService.getBoard().getWinnerPlayer());
        Assertions.assertNotNull(gameService.getBoard().getPits());
        Assertions.assertNotNull(Turn.getPlayerInTurn());
        Assertions.assertNotNull(Turn.getPlayerOpponent());
    }


}
