package com.codurance.model;

import java.util.Set;

public class Game {
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public Player currentPlayer() {
        return Player.BLACK;
    }

    public void placeStoneAt(Board.Intersection intersection) {
        board.placeStoneAt(intersection);
    }

    public Set<Stone> stones() {
        return board.stones();
    }

    public enum Player {
        WHITE, BLACK;
    }
}
