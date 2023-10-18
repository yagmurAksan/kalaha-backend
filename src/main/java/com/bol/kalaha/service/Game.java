package com.bol.kalaha.service;

import com.bol.kalaha.builder.BoardBuilder;
import com.bol.kalaha.builder.BoardCreator;
import com.bol.kalaha.model.Player;
import com.bol.kalaha.utils.SowingNotApplicableException;
import com.bol.kalaha.utils.Turn;
import com.bol.kalaha.model.Board;
import com.bol.kalaha.model.Pit;
import org.springframework.stereotype.Service;

@Service
public class Game {

    private final GameLogic gameLogic;

    private Board board;

    public Game(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public Board getBoard() {
        return board;
    }

    public String create() {
        BoardCreator boardCreator = new BoardCreator();
        BoardBuilder boardBuilder = new BoardBuilder();
        boardCreator.createBoard(boardBuilder);

        board = boardBuilder.getBoard();
        gameLogic.setBoard(board);
        return null;
    }

    public Player makeMove(int pitId) {
        Pit pit = board.getPitById(pitId);

        if(pit.isEmpty()) {
            throw new SowingNotApplicableException("Sowing cannot be started with 0 stone.");
        }

        board.setActivePlayer(Turn.getPlayerInTurn());
        gameLogic.executeSowing(pit);
        if(!board.isStoneLeft()) {
            return gameLogic.determineWinner();
        }
        return null;
    }
}
