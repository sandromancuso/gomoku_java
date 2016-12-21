package com.codurance.model;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.model.Board.intersection;
import static com.codurance.model.BoardBuilder.*;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RulesShould {

    private Board board;
    private Rules rules;

    @Before
    public void initialise() {
        board = new Board();
        rules = new Rules();
    }

    @Test public void
    return_no_winner_where_no_players_have_five_in_a_row() {
        Board emptyBoard = new Board();

        assertThat(rules.winner(emptyBoard).isPresent(), is(false));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_horizontal_line() {
        horizontalStones(5, 1, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        horizontalStones(5, 3, 5, WHITE);
        assertThat(rules.winner(board).get(), is(WHITE));

        resetBoard();
        horizontalStones(4, 1, BLACK);
        assertThat(rules.winner(board).isPresent(), is(false));

        resetBoard();
        horizontalStones(4, 2, BLACK);
        horizontalStones(2, 5, 2, WHITE);
        assertThat(rules.winner(board).isPresent(), is(false));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_vertical_line() {
        verticalStones(5, 1, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        verticalStones(5, 3, 5, WHITE);
        assertThat(rules.winner(board).get(), is(WHITE));

        resetBoard();
        verticalStones(4, 1, BLACK);
        assertThat(rules.winner(board).isPresent(), is(false));

        resetBoard();
        verticalStones(4, 2, BLACK);
        verticalStones(2, 5, 2, WHITE);
        assertThat(rules.winner(board).isPresent(), is(false));
    }

    @Test public void
    return_no_winner_when_containing_five_consecutive_stones_from_different_players() {
        Board board = aBoard()
                            .with(blackStones(at(10, 10), at(11, 11)))
                            .with(whiteStones(at(12, 12)))
                            .with(blackStones(at(13, 13), at(14, 14)))
                            .build();
        assertThat(rules.winner(board).isPresent(), is(false));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_diagonal() {
        board = aBoard().with(blackStones(
                                at(0, 0),
                                at(1, 1),
                                at(2, 2),
                                at(3, 3),
                                at(4, 4))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(10, 10),
                                at(11, 11),
                                at(12, 12),
                                at(13, 13),
                                at(14, 14))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(4, 0),
                                at(5, 1),
                                at(6, 2),
                                at(7, 3),
                                at(8, 4))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(0, 10),
                                at(1, 11),
                                at(2, 12),
                                at(3, 13),
                                at(4, 14))).build();
        assertThat(rules.winner(board).get(), is(BLACK));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_an_anti_diagonal() {
        board = aBoard().with(blackStones(
                                at(14, 0),
                                at(13, 1),
                                at(12, 2),
                                at(11, 3),
                                at(10, 4))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(9, 0),
                                at(8, 1),
                                at(7, 2),
                                at(6, 3),
                                at(5, 4))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(10, 10),
                                at(11, 11),
                                at(12, 12),
                                at(13, 13),
                                at(14, 14))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(0, 10),
                                at(1, 11),
                                at(2, 12),
                                at(3, 13),
                                at(4, 14))).build();
        assertThat(rules.winner(board).get(), is(BLACK));

        board = aBoard().with(blackStones(
                                at(12, 7),
                                at(11, 8),
                                at(10, 9),
                                at(9, 10),
                                at(8, 11))).build();
        assertThat(rules.winner(board).get(), is(BLACK));
    }

    private void resetBoard() {
        board = new Board();
    }

    private void horizontalStones(int numberOfStones, int xOffset, int y, Player player) {
        for (int x = xOffset; x < numberOfStones + xOffset; x++) {
            placeStone(x, y, player);
        }
    }

    private void horizontalStones(int numberOfStones, int y, Player player) {
        horizontalStones(numberOfStones, 0, y, player);
    }

    private void verticalStones(int numberOfStones, int yOffset, int x, Player player) {
        for (int y = yOffset; y < numberOfStones + yOffset; y++) {
            placeStone(x, y, player);
        }
    }

    private void verticalStones(int numberOfStones, int x, Player player) {
        verticalStones(numberOfStones, 0, x, player);
    }

    private void placeStone(int x, int y, Player player) {
        board.placeStoneAt(intersection(x, y).get(), player);
    }

}