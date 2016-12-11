package com.codurance.model;

import java.util.*;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Board {

    public static final int X_INTERSECTIONS = 15;
    public static final int Y_INTERSECTIONS = 15;

    private Set<Stone> stones = new LinkedHashSet<>();

    public static Optional<Intersection> intersection(int x, int y) {
        return withinBoardBounds(x, y)
                ? Optional.of(new Intersection(x, y))
                : Optional.empty();
    }

    public boolean placeStoneAt(Intersection intersection, Player currentPlayer) {
        if (stoneCanBePlacedAt(intersection)) {
            stones.add(new Stone(intersection, currentPlayer));
            return true;
        }
        return false;
    }

    private boolean stoneCanBePlacedAt(Intersection intersection) {
        return stones.stream().noneMatch(s -> s.intersection().equals(intersection));
    }

    public Set<Stone> stones() {
        return Collections.unmodifiableSet(stones);
    }

    private static boolean withinBoardBounds(int x, int y) {
        return x >= 0 && x < X_INTERSECTIONS && y >= 0 && y < Y_INTERSECTIONS;
    }

    public Optional<Stone> stoneAt(Optional<Intersection> intersection) {
        return stones.stream()
                        .filter(s -> s.intersection().equals(intersection.get()))
                        .findFirst();
    }

    public static class Intersection {
        private final int x;
        private final int y;

        Intersection(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            return reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return reflectionHashCode(this);
        }

        @Override
        public String toString() {
            return "Intersection{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

