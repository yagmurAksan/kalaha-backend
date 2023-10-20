package com.bol.kalaha.integration;

import com.bol.kalaha.controller.GameController;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.service.SowingService;
import com.bol.kalaha.utils.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mockStatic;
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
    void should_getResponseSuccessfully_when_startGameRestCall() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/startGame")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_getResponseSuccessfully_when_makeMoveRestCall() throws Exception {

        turn.when(Turn::getPlayerInTurn).thenReturn(new Player(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/makeMove/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}