package com.bol.kalaha.integration;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameController.class)
class TestAPIGameControllerService {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private SowingService sowingService;

    @Test
    void should_getResponseSuccessfully_when_startGameRestCall() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/startGame")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_getResponseSuccessfully_when_makeMoveRestCall() throws Exception {
        Board board = new Board();
        board.setActivePlayer(new Player(1));

        when(gameService.getBoard()).thenReturn(board);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/makeMove/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}