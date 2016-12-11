package com.codurance.model;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Stone {
    private Board.Intersection intersection;
    private Player player;

    public Stone(Board.Intersection intersection, Player player) {
        this.intersection = intersection;
        this.player = player;
    }

    public Board.Intersection intersection() {
        return intersection;
    }

    public Player player() {
        return player;
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
        return "Stone{" +
                "intersection=" + intersection +
                ", player=" + player +
                '}';
    }
}
