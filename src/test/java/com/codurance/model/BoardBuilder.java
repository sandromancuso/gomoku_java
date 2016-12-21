package com.codurance.model;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;

public class BoardBuilder {

    private List<Stone> stones = new ArrayList<>();

    static BoardBuilder aBoard() {
        return new BoardBuilder();
    }

    BoardBuilder with(List<Stone> stones) {
        this.stones.addAll(stones);
        return this;
    }

    static List<Stone> blackStones(Board.Intersection... intersections) {
        List<Stone> stones = new ArrayList<>();
        for (Board.Intersection intersection : intersections) {
            stones.add(new Stone(intersection, BLACK));
        }
        return stones;
    }

    static List<Stone> whiteStones(Board.Intersection... intersections) {
        List<Stone> stones = new ArrayList<>();
        for (Board.Intersection intersection : intersections) {
            stones.add(new Stone(intersection, WHITE));
        }
        return stones;
    }

    static Board.Intersection at(int x, int y) {
        return Board.intersection(x, y).get();
    }

    Board build() {
        Board board = new Board();
        addStonesTo(board);
        return board;
    }

    private void addStonesTo(Board board) {
        for (Stone stone : stones) {
            board.placeStoneAt(stone.intersection(), stone.player());
        }
    }

}
