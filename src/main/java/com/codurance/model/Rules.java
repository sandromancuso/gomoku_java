package com.codurance.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codurance.model.Board.X_INTERSECTIONS;
import static com.codurance.model.Board.Y_INTERSECTIONS;
import static com.codurance.model.Board.intersection;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
import static java.util.Collections.indexOfSubList;

public class Rules {

    private static final List<Player> FIVE_BLACK = Arrays.asList(BLACK, BLACK, BLACK, BLACK, BLACK);
    private static final List<Player> FIVE_WHITE = Arrays.asList(WHITE, WHITE, WHITE, WHITE, WHITE);


    public Optional<Player> winner(Board board) {
        Optional<Player> winner = winnerForHorizontalLines(board);
        if (!winner.isPresent()) {
            winner = winnerForVerticalLines(board);
        }
        return winner;
    }

    private Optional<Player> winnerForVerticalLines(Board board) {
        Optional<Player> winner = Optional.empty();
        List<Optional<Stone>> boardStones = new ArrayList<>();
        for (int x = 0; x < X_INTERSECTIONS - 1; x++) {
            for (int y = 0; y < Y_INTERSECTIONS - 1; y++) {
                boardStones.add(board.stoneAt(intersection(x, y)));
            }
            winner = winnerFor(boardStones);
            if (winner.isPresent()) {
                return winner;
            }
            boardStones.clear();
        }
        return winner;
    }

    private Optional<Player> winnerForHorizontalLines(Board board) {
        Optional<Player> winner = Optional.empty();
        List<Optional<Stone>> boardStones = new ArrayList<>();
        for (int y = 0; y < Y_INTERSECTIONS - 1; y++) {
            for (int x = 0; x < X_INTERSECTIONS - 1; x++) {
                boardStones.add(board.stoneAt(intersection(x, y)));
            }
            winner = winnerFor(boardStones);
            if (winner.isPresent()) {
                return winner;
            }
            boardStones.clear();
        }
        return winner;
    }

    private Optional<Player> winnerFor(List<Optional<Stone>> boardStones) {
        List<Player> players = boardStones.stream()
                .map(optionalStone -> (optionalStone.isPresent())
                        ? optionalStone.get().player()
                        : null)
                .collect(Collectors.toList());

        if (indexOfSubList(players, FIVE_BLACK) >= 0) {
            return Optional.of(BLACK);
        } else if (indexOfSubList(players, FIVE_WHITE) >= 0) {
            return Optional.of(WHITE);
        } else {
            return Optional.empty();
        }
    }
}
