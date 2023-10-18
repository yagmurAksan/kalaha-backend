package com.bol.kalaha.utils;

import com.bol.kalaha.model.Player;

public class Turn {
    private static Player playerInTurn;
    private static Player playerOpponent;
    private static boolean willChange = true;

    public static Player getPlayerInTurn() {
        return playerInTurn;
    }

    public static void setPlayerInTurn(Player playerInTurn) {
        Turn.playerInTurn = playerInTurn;
    }

    public static Player getPlayerOpponent() {
        return playerOpponent;
    }

    public static void setPlayerOpponent(Player playerOpponent) {
        Turn.playerOpponent = playerOpponent;
    }

    public static boolean isWillChange() {
        return willChange;
    }

    public static void turnEndedOnBigPit() {
        willChange = false;
    }

    public static Player getNextPlayer() {
        if(willChange) {
            playerOpponent = playerOpponent.getOpponent();
            playerInTurn = playerInTurn.getOpponent();
        }
        willChange = true;
        return playerInTurn;
    }
}
