package com.codurance;

import com.codurance.Board.Intersection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardShould {

    @Test
    @Parameters({
            "10, 10",
            " 3,  2",
            " 0,  0",
            "14, 14"
    })
    public void
    create_intersection_for_a_valid_x_and_y_coordinate(int x, int y) {
        assertThat(Board.intersection(x, y).get(), is(new Intersection(x, y)));
    }

    @Test
    @Parameters({
            "-1, 10",
            "15, 10",
            "16, 10",
            " 5, -1",
            " 9, 15",
            " 9, 16"
    })
    public void
    not_create_an_intersection_in_an_invalid_coordinate(int x, int y) {
        assertThat(Board.intersection(x, y), is(Optional.empty()));
    }
}