package com.bol.kalaha.service;

import com.bol.kalaha.builder.BoardBuilder;
import com.bol.kalaha.builder.BoardCreator;
import com.bol.kalaha.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final SowingService sowingService;

    private Board board;

    public Board getBoard() {
        return board;
    }

    public void create() {
        BoardCreator boardCreator = new BoardCreator();
        BoardBuilder boardBuilder = new BoardBuilder();
        boardCreator.createBoard(boardBuilder);

        board = boardBuilder.getBoard();
        sowingService.setBoard(board);
    }
}
