package com.codurance.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codurance.model.Board.*;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
import static java.util.Collections.indexOfSubList;

public class Rules {

    private static final List<Player> FIVE_BLACK = Arrays.asList(BLACK, BLACK, BLACK, BLACK, BLACK);
    private static final List<Player> FIVE_WHITE = Arrays.asList(WHITE, WHITE, WHITE, WHITE, WHITE);


    public Optional<Player> winner(Board board) {
        Optional<Player> winner = winnerForHorizontalLines(board);
        if (winner.isPresent()) {
            return winner;
        }

        winner = winnerForVerticalLines(board);
        if (winner.isPresent()) {
            return winner;
        }

        return winnerForDiagonalLines(board);
    }

    private Optional<Player> winnerForVerticalLines(Board board) {
        List<List<Optional<Stone>>> verticalLines = new ArrayList<>();
        List<Optional<Stone>> verticalLine;
        for (int y = 0; y < Y_INTERSECTIONS - 1; y++) {
            verticalLine = new ArrayList<>();
            for (int x = 0; x < X_INTERSECTIONS - 1; x++) {
                verticalLine.add(board.stoneAt(intersection(x, y)));
            }
            verticalLines.add(verticalLine);
        }
        return winnerFor(verticalLines);
    }

    private Optional<Player> winnerForHorizontalLines(Board board) {
        List<List<Optional<Stone>>> horizontalLines = new ArrayList<>();
        List<Optional<Stone>> horizontalLine;
        for (int x = 0; x < X_INTERSECTIONS - 1; x++) {
            horizontalLine = new ArrayList<>();
            for (int y = 0; y < Y_INTERSECTIONS - 1; y++) {
                horizontalLine.add(board.stoneAt(intersection(x, y)));
            }
            horizontalLines.add(horizontalLine);
        }
        return winnerFor(horizontalLines);
    }

    private Optional<Player> winnerForDiagonalLines(Board board) {
        List<List<Optional<Stone>>> lines = diagonalTopLeftToBottomRight(board);
        lines.addAll(diagonalTopRightToBottomLeft(board));
        return winnerFor(lines);
    }

    private List<List<Optional<Stone>>> diagonalTopLeftToBottomRight(Board board) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        for (int xOffset = 0; xOffset <= X_INTERSECTIONS - 5; xOffset++) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = xOffset, y = 0; x <= X_INTERSECTIONS - 1; x++, y++) {
                line.add(board.stoneAt(Board.intersection(x, y)));
            }
            lines.add(line);
        }

        for (int yOffset = 0; yOffset <= Y_INTERSECTIONS - 5; yOffset++) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = 0, y = yOffset; y <= Y_INTERSECTIONS - 1; x++, y++) {
                line.add(board.stoneAt(Board.intersection(x, y)));
            }
            lines.add(line);
        }
        return lines;
    }

    private List<List<Optional<Stone>>> diagonalTopRightToBottomLeft(Board board) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        for (int xOffset = X_INTERSECTIONS - 1; xOffset >= 4; xOffset--) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = xOffset, y = 0; x >= 0; x--, y++) {
                line.add(board.stoneAt(Board.intersection(x, y)));
            }
            lines.add(line);
        }

        for (int yOffset = 0; yOffset <= Y_INTERSECTIONS - 5; yOffset++) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = X_INTERSECTIONS - 1, y = yOffset; y <= Y_INTERSECTIONS - 1; x--, y++) {
                line.add(board.stoneAt(Board.intersection(x, y)));
            }
            lines.add(line);
        }
        return lines;
    }

    private Optional<Player> winnerFor(List<List<Optional<Stone>>> lines) {
        Optional<Player> winner = Optional.empty();
        for (List<Optional<Stone>> line : lines) {
            winner = winnerForLine(line);
            if (winner.isPresent()) {
                return winner;
            }
        }
        return winner;
    }

    private Optional<Player> winnerForLine(List<Optional<Stone>> boardLine) {
        List<Player> stonesInLine = boardLine.stream()
                .map(optionalStone -> (optionalStone.isPresent())
                        ? optionalStone.get().player()
                        : null)
                .collect(Collectors.toList());

        if (indexOfSubList(stonesInLine, FIVE_BLACK) >= 0) {
            return Optional.of(BLACK);
        } else if (indexOfSubList(stonesInLine, FIVE_WHITE) >= 0) {
            return Optional.of(WHITE);
        } else {
            return Optional.empty();
        }
    }
}
