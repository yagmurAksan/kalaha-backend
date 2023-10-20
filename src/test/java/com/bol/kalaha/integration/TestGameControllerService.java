package com.bol.kalaha.integration;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.controller.dto.GameResponseDTO;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGameControllerService {

    private GameController gameController;

    private GameService gameService;

    private SowingService sowingService;

    @BeforeEach
    public void beforeTest() {
        sowingService = new SowingService();
        gameService = new GameService(sowingService);
        gameController = new GameController(gameService, sowingService);
    }

    @Test
    void should_createGameResponseDTO_when_startGame() {

        GameResponseDTO gameResponseDTO = gameController.startGame();

        Assertions.assertEquals(14, gameResponseDTO.pitList().size());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(0).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(1).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(1).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.pitList().get(2).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(2).numberOfStones());
        Assertions.assertEquals(3, gameResponseDTO.pitList().get(3).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(3).numberOfStones());
        Assertions.assertEquals(4, gameResponseDTO.pitList().get(4).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(4).numberOfStones());
        Assertions.assertEquals(5, gameResponseDTO.pitList().get(5).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(5).numberOfStones());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(6).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(6).numberOfStones());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(7).numberOfStones());
        Assertions.assertEquals(8, gameResponseDTO.pitList().get(8).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(8).numberOfStones());
        Assertions.assertEquals(9, gameResponseDTO.pitList().get(9).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(9).numberOfStones());
        Assertions.assertEquals(10, gameResponseDTO.pitList().get(10).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(10).numberOfStones());
        Assertions.assertEquals(11, gameResponseDTO.pitList().get(11).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(11).numberOfStones());
        Assertions.assertEquals(12, gameResponseDTO.pitList().get(12).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(12).numberOfStones());
        Assertions.assertEquals(13, gameResponseDTO.pitList().get(13).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(13).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.playerInTurn().id());
        Assertions.assertNull(gameResponseDTO.playerWinner());
    }

    @Test
    void should_createGameResponseDTOWithNoTurnChange_when_makeMove0() {

        gameService.create();
        GameResponseDTO gameResponseDTO = gameController.makeMove(0);

        Assertions.assertEquals(14, gameResponseDTO.pitList().size());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(1).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(1).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.pitList().get(2).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(2).numberOfStones());
        Assertions.assertEquals(3, gameResponseDTO.pitList().get(3).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(3).numberOfStones());
        Assertions.assertEquals(4, gameResponseDTO.pitList().get(4).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(4).numberOfStones());
        Assertions.assertEquals(5, gameResponseDTO.pitList().get(5).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(5).numberOfStones());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(6).id());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(6).numberOfStones());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(7).numberOfStones());
        Assertions.assertEquals(8, gameResponseDTO.pitList().get(8).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(8).numberOfStones());
        Assertions.assertEquals(9, gameResponseDTO.pitList().get(9).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(9).numberOfStones());
        Assertions.assertEquals(10, gameResponseDTO.pitList().get(10).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(10).numberOfStones());
        Assertions.assertEquals(11, gameResponseDTO.pitList().get(11).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(11).numberOfStones());
        Assertions.assertEquals(12, gameResponseDTO.pitList().get(12).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(12).numberOfStones());
        Assertions.assertEquals(13, gameResponseDTO.pitList().get(13).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(13).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.playerInTurn().id());
        Assertions.assertNull(gameResponseDTO.playerWinner());
    }

    @Test
    void should_createGameResponseDTOWithWinner_when_makeMove0() {

        gameService.create();
        gameService.getBoard().getPits().forEach(pit->pit.setNumOfStones(0));
        gameService.getBoard().getPits().get(0).setNumOfStones(1);
        GameResponseDTO gameResponseDTO = gameController.makeMove(0);

        Assertions.assertEquals(14, gameResponseDTO.pitList().size());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(1).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(1).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.pitList().get(2).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(2).numberOfStones());
        Assertions.assertEquals(3, gameResponseDTO.pitList().get(3).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(3).numberOfStones());
        Assertions.assertEquals(4, gameResponseDTO.pitList().get(4).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(4).numberOfStones());
        Assertions.assertEquals(5, gameResponseDTO.pitList().get(5).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(5).numberOfStones());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(6).id());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(6).numberOfStones());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(7).numberOfStones());
        Assertions.assertEquals(8, gameResponseDTO.pitList().get(8).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(8).numberOfStones());
        Assertions.assertEquals(9, gameResponseDTO.pitList().get(9).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(9).numberOfStones());
        Assertions.assertEquals(10, gameResponseDTO.pitList().get(10).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(10).numberOfStones());
        Assertions.assertEquals(11, gameResponseDTO.pitList().get(11).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(11).numberOfStones());
        Assertions.assertEquals(12, gameResponseDTO.pitList().get(12).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(12).numberOfStones());
        Assertions.assertEquals(13, gameResponseDTO.pitList().get(13).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(13).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.playerInTurn().id());
        Assertions.assertEquals(1, gameResponseDTO.playerWinner().id());
    }

    @Test
    void should_createGameResponseDTOWithCollectingFromOpposite_when_makeMove0() {

        gameService.create();
        gameService.getBoard().getPits().get(0).setNumOfStones(1);
        gameService.getBoard().getPits().get(1).setNumOfStones(0);
        GameResponseDTO gameResponseDTO = gameController.makeMove(0);

        Assertions.assertEquals(14, gameResponseDTO.pitList().size());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(1).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(1).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.pitList().get(2).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(2).numberOfStones());
        Assertions.assertEquals(3, gameResponseDTO.pitList().get(3).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(3).numberOfStones());
        Assertions.assertEquals(4, gameResponseDTO.pitList().get(4).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(4).numberOfStones());
        Assertions.assertEquals(5, gameResponseDTO.pitList().get(5).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(5).numberOfStones());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(6).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(6).numberOfStones());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(7).numberOfStones());
        Assertions.assertEquals(8, gameResponseDTO.pitList().get(8).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(8).numberOfStones());
        Assertions.assertEquals(9, gameResponseDTO.pitList().get(9).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(9).numberOfStones());
        Assertions.assertEquals(10, gameResponseDTO.pitList().get(10).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(10).numberOfStones());
        Assertions.assertEquals(11, gameResponseDTO.pitList().get(11).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(11).numberOfStones());
        Assertions.assertEquals(12, gameResponseDTO.pitList().get(12).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(12).numberOfStones());
        Assertions.assertEquals(13, gameResponseDTO.pitList().get(13).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(13).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.playerInTurn().id());
        Assertions.assertNull(gameResponseDTO.playerWinner());
    }

    @Test
    void should_createGameResponseDTOWithTurnChange_when_makeMove1() {

        gameService.create();
        GameResponseDTO gameResponseDTO = gameController.makeMove(1);

        Assertions.assertEquals(14, gameResponseDTO.pitList().size());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(0).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(0).numberOfStones());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(1).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(1).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.pitList().get(2).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(2).numberOfStones());
        Assertions.assertEquals(3, gameResponseDTO.pitList().get(3).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(3).numberOfStones());
        Assertions.assertEquals(4, gameResponseDTO.pitList().get(4).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(4).numberOfStones());
        Assertions.assertEquals(5, gameResponseDTO.pitList().get(5).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(5).numberOfStones());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(6).id());
        Assertions.assertEquals(1, gameResponseDTO.pitList().get(6).numberOfStones());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).id());
        Assertions.assertEquals(7, gameResponseDTO.pitList().get(7).numberOfStones());
        Assertions.assertEquals(8, gameResponseDTO.pitList().get(8).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(8).numberOfStones());
        Assertions.assertEquals(9, gameResponseDTO.pitList().get(9).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(9).numberOfStones());
        Assertions.assertEquals(10, gameResponseDTO.pitList().get(10).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(10).numberOfStones());
        Assertions.assertEquals(11, gameResponseDTO.pitList().get(11).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(11).numberOfStones());
        Assertions.assertEquals(12, gameResponseDTO.pitList().get(12).id());
        Assertions.assertEquals(6, gameResponseDTO.pitList().get(12).numberOfStones());
        Assertions.assertEquals(13, gameResponseDTO.pitList().get(13).id());
        Assertions.assertEquals(0, gameResponseDTO.pitList().get(13).numberOfStones());
        Assertions.assertEquals(2, gameResponseDTO.playerInTurn().id());
        Assertions.assertNull(gameResponseDTO.playerWinner());
    }
}