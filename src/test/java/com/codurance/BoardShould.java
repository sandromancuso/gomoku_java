package com.codurance;

import com.codurance.Board.Intersection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardShould {

    private Board board;

    @Before
    public void initialise() {
        board = new Board();
    }

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

    @Test public void
    accept_accept_stones_to_be_placed_on_a_given_intersection() {
        Intersection intersection_1x1 = Board.intersection(1, 1).get();
        Intersection intersection_2x2 = Board.intersection(2, 2).get();
        Stone stone_at_1x1 = new Stone(intersection_1x1);
        Stone stone_at_2x2 = new Stone(intersection_2x2);

        board.placeStoneAt(intersection_1x1);
        board.placeStoneAt(intersection_2x2);

        assertThat(board.stones().contains(stone_at_1x1), is(true));
        assertThat(board.stones().contains(stone_at_2x2), is(true));
    }

    @Test public void
    not_accept_more_than_one_stone_on_an_intersection() {
        Intersection intersection_1x1 = Board.intersection(1, 1).get();
        Stone stone_at_1x1 = new Stone(intersection_1x1);

        board.placeStoneAt(intersection_1x1);
        board.placeStoneAt(intersection_1x1);

        assertThat(board.stones().contains(stone_at_1x1), is(true));
        assertThat(board.stones().size(), is(1));
    }

}