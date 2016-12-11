package com.codurance.model;

import com.codurance.model.Board.Intersection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static com.codurance.model.Board.intersection;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
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
        assertThat(intersection(x, y).get(), is(new Intersection(x, y)));
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
        assertThat(intersection(x, y), is(Optional.empty()));
    }

    @Test public void
    accept_accept_stones_to_be_placed_on_a_given_intersection() {
        Intersection intersection_1x1 = intersection(1, 1).get();
        Intersection intersection_2x2 = intersection(2, 2).get();
        Stone stone_at_1x1 = new Stone(intersection_1x1, BLACK);
        Stone stone_at_2x2 = new Stone(intersection_2x2, Player.WHITE);

        board.placeStoneAt(intersection_1x1, BLACK);
        board.placeStoneAt(intersection_2x2, Player.WHITE);

        assertThat(board.stones().contains(stone_at_1x1), is(true));
        assertThat(board.stones().contains(stone_at_2x2), is(true));
    }

    @Test public void
    not_accept_more_than_one_stone_on_an_intersection() {
        Intersection intersection_1x1 = intersection(1, 1).get();
        Stone stone_at_1x1 = new Stone(intersection_1x1, BLACK);

        board.placeStoneAt(intersection_1x1, BLACK);
        board.placeStoneAt(intersection_1x1, BLACK);
        board.placeStoneAt(intersection_1x1, Player.WHITE);

        assertThat(board.stones().contains(stone_at_1x1), is(true));
        assertThat(board.stones().size(), is(1));
    }

    @Test public void
    return_a_stone_placed_on_an_given_intersection() {
        Stone black = new Stone(intersection(0, 0).get(), BLACK);
        Stone white = new Stone(intersection(2, 2).get(), WHITE);

        board.placeStoneAt(black.intersection(), black.player());
        board.placeStoneAt(white.intersection(), white.player());

        assertThat(board.stoneAt(intersection(0, 0)), is(Optional.of(black)));
        assertThat(board.stoneAt(intersection(1, 1)), is(Optional.empty()));
        assertThat(board.stoneAt(intersection(2, 2)), is(Optional.of(white)));
    }

}