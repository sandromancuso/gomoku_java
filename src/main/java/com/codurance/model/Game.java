package com.codurance.model;

import java.util.Set;

public class Game {
    private Board board;
    private Player currentPlayer = Player.BLACK;

    public Game(Board board) {
        this.board = board;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public void placeStoneAt(Board.Intersection intersection) {
        if (board.placeStoneAt(intersection, currentPlayer)) {
            switchPlayers();
        }
    }

    private void switchPlayers() {
        currentPlayer = (currentPlayer() == Player.BLACK) ? Player.WHITE : Player.BLACK;
    }

    public Set<Stone> stones() {
        return board.stones();
    }

}
