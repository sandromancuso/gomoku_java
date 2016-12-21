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
        return stones((intersection) -> new Stone(intersection, BLACK), intersections);
    }

    static List<Stone> whiteStones(Board.Intersection... intersections) {
        return stones((intersection) -> new Stone(intersection, WHITE), intersections);
    }

    static Board.Intersection at(int x, int y) {
        return Board.intersection(x, y).get();
    }

    static List<Stone> horizontalStones(int numberOfStones, int xOffset, int y, Player player) {
        List<Stone> stones = new ArrayList<>();
        for (int x = xOffset; x < numberOfStones + xOffset; x++) {
            stones.add(new Stone(at(x, y), player));
        }
        return stones;
    }

    static List<Stone> horizontalStones(int numberOfStones, int y, Player player) {
        return horizontalStones(numberOfStones, 0, y, player);
    }

    static List<Stone> verticalStones(int numberOfStones, int yOffset, int x, Player player) {
        List<Stone> stones = new ArrayList<>();
        for (int y = yOffset; y < numberOfStones + yOffset; y++) {
            stones.add(new Stone(at(x, y), player));
        }
        return stones;
    }

    static List<Stone> verticalStones(int numberOfStones, int x, Player player) {
        return verticalStones(numberOfStones, 0, x, player);
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

    interface CreateStone {
        Stone create(Board.Intersection intersection);
    }

    private static List<Stone> stones(CreateStone createStone, Board.Intersection... intersections) {
        List<Stone> stones = new ArrayList<>();
        for (Board.Intersection intersection : intersections) {
            stones.add(createStone.create(intersection));
        }
        return stones;

    }

}
