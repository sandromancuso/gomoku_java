package com.codurance.model;

import java.util.Optional;
import java.util.Set;

public class Game {
    private Board board;
    private Rules rules;
    private Player currentPlayer = Player.BLACK;

    public Game(Board board, Rules rules) {
        this.board = board;
        this.rules = rules;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public void placeStoneAt(Board.Intersection intersection) {
        if (board.placeStoneAt(intersection, currentPlayer)) {
            switchPlayers();
        }
    }

    public Set<Stone> stones() {
        return board.stones();
    }

    private void switchPlayers() {
        currentPlayer = (currentPlayer() == Player.BLACK) ? Player.WHITE : Player.BLACK;
    }

    public Optional<Player> winner() {
        return rules.winner(board);
    }
}
