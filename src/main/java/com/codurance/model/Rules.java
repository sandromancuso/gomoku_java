package com.codurance.model;

import java.util.*;
import java.util.stream.Collectors;

import static com.codurance.model.Board.*;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
import static java.util.Collections.indexOfSubList;

public class Rules {

    private static final List<Player> FIVE_BLACK = Arrays.asList(BLACK, BLACK, BLACK, BLACK, BLACK);
    private static final List<Player> FIVE_WHITE = Arrays.asList(WHITE, WHITE, WHITE, WHITE, WHITE);

    public Optional<Player> winner(Board board) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        lines.addAll(straightLines((x, y) -> board.stoneAt(intersection(x, y).get())));
        lines.addAll(straightLines((x, y) -> board.stoneAt(intersection(y, x).get())));
        lines.addAll(diagonalLines((x, y) -> board.stoneAt(intersection(x, y).get())));
        lines.addAll(antiDiagonalLines((x, y) -> board.stoneAt(intersection(x, y).get())));
        return winnerFor(lines);
    }

    interface StoneRetriever {
        Optional<Stone> stoneAt(int x, int y);
    }

    private List<List<Optional<Stone>>> straightLines(StoneRetriever stoneRetriever) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        List<Optional<Stone>> line;
        for (int i = 0; i < X_INTERSECTIONS - 1; i++) {
            line = new ArrayList<>();
            for (int j = 0; j < Y_INTERSECTIONS - 1; j++) {
                line.add(stoneRetriever.stoneAt(i, j));
            }
            lines.add(line);
        }
        return lines;
    }

    private List<List<Optional<Stone>>> diagonalLines(StoneRetriever stoneRetriever) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        List<Optional<Stone>> line;
        for (int xOffset = 0; xOffset <= X_INTERSECTIONS - 5; xOffset++) {
            line = new ArrayList<>();
            for (int x = xOffset, y = 0; x <= X_INTERSECTIONS - 1; x++, y++) {
                line.add(stoneRetriever.stoneAt(x, y));
            }
            lines.add(line);
        }

        for (int yOffset = 0; yOffset <= Y_INTERSECTIONS - 5; yOffset++) {
            line = new ArrayList<>();
            for (int x = 0, y = yOffset; y <= Y_INTERSECTIONS - 1; x++, y++) {
                line.add(stoneRetriever.stoneAt(x, y));
            }
            lines.add(line);
        }
        return lines;
    }

    private List<List<Optional<Stone>>> antiDiagonalLines(StoneRetriever stoneRetriever) {
        List<List<Optional<Stone>>> lines = new ArrayList<>();
        for (int xOffset = X_INTERSECTIONS - 1; xOffset >= 4; xOffset--) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = xOffset, y = 0; x >= 0; x--, y++) {
                line.add(stoneRetriever.stoneAt(x, y));
            }
            lines.add(line);
        }

        for (int yOffset = 0; yOffset <= Y_INTERSECTIONS - 5; yOffset++) {
            List<Optional<Stone>> line = new ArrayList<>();
            for (int x = X_INTERSECTIONS - 1, y = yOffset; y <= Y_INTERSECTIONS - 1; x--, y++) {
                line.add(stoneRetriever.stoneAt(x, y));
            }
            lines.add(line);
        }
        return lines;
    }

    private Optional<Player> winnerFor(List<List<Optional<Stone>>> lines) {
        return lines.stream()
                    .map(this::winnerForLine)
                    .filter(Optional::isPresent)
                    .findAny()
                    .orElseGet(Optional::empty);
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
