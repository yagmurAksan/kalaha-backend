package com.bol.kalaha.controller;

import com.bol.kalaha.controller.dto.GameResponseDTO;
import com.bol.kalaha.service.GameService;
import com.bol.kalaha.controller.mapper.GameMapper;
import com.bol.kalaha.service.SowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class GameController {
    private final GameService gameService;

    private final SowingService sowingService;

    public GameController(GameService gameService, SowingService sowingService) {
        this.gameService = gameService;
        this.sowingService = sowingService;
    }

    @Operation(summary = "Create a game with initial values")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Initial game values are created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameResponseDTO.class)) }) })
    @GetMapping("/startGame")
    public GameResponseDTO startGame() {
        log.info("Invoking startGame() endpoint...");

        gameService.create();

        log.info("Game is created with the initial values.");

        return GameMapper.INSTANCE.toDto(gameService.getBoard());
    }

    @Operation(summary = "Make a move in the game by one of the little pit ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Move is made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid pit id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Suitable Pit for sowing is not found",
                    content = @Content) })
    @GetMapping("/makeMove/{id}")
    public GameResponseDTO makeMove(@Parameter(description = "id of pit for sowing") @PathVariable int id) {
        log.info("Invoking makeMove() endpoint...");

        sowingService.executeSowing(id);

        log.info("Move is made for pit id: " + id);
        log.info("Player in Turn id: " + gameService.getBoard().getActivePlayer().getId());

        return GameMapper.INSTANCE.toDto(gameService.getBoard());
    }
}
