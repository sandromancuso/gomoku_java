package com.codurance;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Board {

    public static final int X_INTERSECTIONS = 15;
    public static final int Y_INTERSECTIONS = 15;

    public static Optional<Intersection> intersection(int x, int y) {
        return (x >= 0 && x < X_INTERSECTIONS && y >= 0 && y < Y_INTERSECTIONS)
                ? Optional.of(new Intersection(x, y))
                : Optional.empty();
    }

    public void placeStoneAt(Intersection intersection) {
        throw new UnsupportedOperationException();
    }

    public List<Stone> stones() {
        throw new UnsupportedOperationException();
    }

    public static class Stone {
        private Intersection intersection;

        public Stone(Intersection intersection) {
            this.intersection = intersection;
        }

        public Intersection intersection() {
            return intersection;
        }
    }

    public static class Intersection {
        private final int x;
        private final int y;

        public Intersection(int x, int y) {
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

