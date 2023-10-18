package com.bol.kalaha.controller;

import com.bol.kalaha.controller.dto.GameResponseDTO;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.service.Game;
import com.bol.kalaha.controller.mapper.GameMapper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @GetMapping("/startGame")
    public GameResponseDTO startGame() {
        game.create();

        return GameMapper.INSTANCE.toDto(game.getBoard(), null);
    }

    @GetMapping("/makeMove/{id}")
    public GameResponseDTO makeMove(@PathVariable int id) {
        Player winner = game.makeMove(id);

        return GameMapper.INSTANCE.toDto(game.getBoard(), winner);
    }
}
