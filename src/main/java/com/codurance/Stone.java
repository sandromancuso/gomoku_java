package com.codurance;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

public class Stone {
    private Board.Intersection intersection;

    public Stone(Board.Intersection intersection) {
        this.intersection = intersection;
    }

    public Board.Intersection intersection() {
        return intersection;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

}
