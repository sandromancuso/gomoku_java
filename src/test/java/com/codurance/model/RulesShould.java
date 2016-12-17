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

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_diagonal() {
        placeStone(0, 0, BLACK);
        placeStone(1, 1, BLACK);
        placeStone(2, 2, BLACK);
        placeStone(3, 3, BLACK);
        placeStone(4, 4, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(10, 10, BLACK);
        placeStone(11, 11, BLACK);
        placeStone(12, 12, BLACK);
        placeStone(13, 13, BLACK);
        placeStone(14, 14, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(4, 0, BLACK);
        placeStone(5, 1, BLACK);
        placeStone(6, 2, BLACK);
        placeStone(7, 3, BLACK);
        placeStone(8, 4, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(10, 10, BLACK);
        placeStone(11, 11, BLACK);
        placeStone(12, 12, WHITE);
        placeStone(13, 13, BLACK);
        placeStone(14, 14, BLACK);
        assertThat(rules.winner(board).isPresent(), is(false));

        resetBoard();
        placeStone(0, 10, BLACK);
        placeStone(1, 11, BLACK);
        placeStone(2, 12, BLACK);
        placeStone(3, 13, BLACK);
        placeStone(4, 14, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_an_anti_diagonal() {
        placeStone(14, 0, BLACK);
        placeStone(13, 1, BLACK);
        placeStone(12, 2, BLACK);
        placeStone(11, 3, BLACK);
        placeStone(10, 4, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(9, 0, BLACK);
        placeStone(8, 1, BLACK);
        placeStone(7, 2, BLACK);
        placeStone(6, 3, BLACK);
        placeStone(5, 4, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(10, 10, BLACK);
        placeStone(11, 11, BLACK);
        placeStone(12, 12, BLACK);
        placeStone(13, 13, BLACK);
        placeStone(14, 14, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        resetBoard();
        placeStone(0, 10, BLACK);
        placeStone(1, 11, BLACK);
        placeStone(2, 12, BLACK);
        placeStone(3, 13, BLACK);
        placeStone(4, 14, BLACK);
        assertThat(rules.winner(board).get(), is(BLACK));

        placeStone(12, 7, BLACK);
        placeStone(11, 8, BLACK);
        placeStone(10, 9, BLACK);
        placeStone( 9, 10, BLACK);
        placeStone( 8, 11, BLACK);
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