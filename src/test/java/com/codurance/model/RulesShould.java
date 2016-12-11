package com.codurance.model;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.model.Board.intersection;
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

    private void resetBoard() {
        board = new Board();
    }

    private void horizontalStones(int numberOfStones, int xOffset, int y, Player player) {
        for (int x = xOffset; x < numberOfStones + xOffset; x++) {
            board.placeStoneAt(intersection(x, y).get(), player);
        }
    }

    private void horizontalStones(int numberOfStones, int y, Player player) {
        horizontalStones(numberOfStones, 0, y, player);
    }

    private void verticalStones(int numberOfStones, int yOffset, int x, Player player) {
        for (int y = yOffset; y < numberOfStones + yOffset; y++) {
            board.placeStoneAt(intersection(x, y).get(), player);
        }
    }

    private void verticalStones(int numberOfStones, int x, Player player) {
        horizontalStones(numberOfStones, 0, x, player);
    }

}