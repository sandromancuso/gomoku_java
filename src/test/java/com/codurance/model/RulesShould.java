package com.codurance.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.codurance.model.BoardBuilder.*;
import static com.codurance.model.Player.BLACK;
import static com.codurance.model.Player.WHITE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RulesShould {

    private Player NO_WINNER = null;

    private Rules rules;

    @Before
    public void initialise() {
        rules = new Rules();
    }

    @Test public void
    return_no_winner_where_no_players_have_five_in_a_row() {
        Board emptyBoard = new Board();

        assertThat(winnerForThe(emptyBoard), is(NO_WINNER));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_horizontal_line() {
        Board board = aBoard().with(horizontalStones(5, 1, BLACK)).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(horizontalStones(5, 3, 5, WHITE)).build();
        assertThat(winnerForThe(board), is(WHITE));

        board = aBoard().with(horizontalStones(4, 1, BLACK)).build();
        assertThat(winnerForThe(board), is(NO_WINNER));

        board = aBoard()
                    .with(horizontalStones(4, 2, BLACK))
                    .with(horizontalStones(2, 5, 2, WHITE)).build();
        assertThat(winnerForThe(board), is(NO_WINNER));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_vertical_line() {
        Board board = aBoard().with(verticalStones(5, 1, BLACK)).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(verticalStones(5, 3, 5, WHITE)).build();
        assertThat(winnerForThe(board), is(WHITE));

        board = aBoard().with(verticalStones(4, 1, BLACK)).build();
        assertThat(winnerForThe(board), is(NO_WINNER));

        board = aBoard()
                    .with(verticalStones(4, 2, BLACK))
                    .with(verticalStones(2, 5, 2, WHITE)).build();
        assertThat(winnerForThe(board), is(NO_WINNER));
    }

    @Test public void
    return_no_winner_when_containing_five_consecutive_stones_from_different_players() {
        Board board = aBoard()
                            .with(blackStones(at(10, 10), at(11, 11)))
                            .with(whiteStones(at(12, 12)))
                            .with(blackStones(at(13, 13), at(14, 14)))
                            .build();
        assertThat(winnerForThe(board), is(NO_WINNER));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_a_diagonal() {
        Board board = aBoard().with(blackStones(
                                at(0, 0),
                                at(1, 1),
                                at(2, 2),
                                at(3, 3),
                                at(4, 4))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(10, 10),
                                at(11, 11),
                                at(12, 12),
                                at(13, 13),
                                at(14, 14))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(4, 0),
                                at(5, 1),
                                at(6, 2),
                                at(7, 3),
                                at(8, 4))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(0, 10),
                                at(1, 11),
                                at(2, 12),
                                at(3, 13),
                                at(4, 14))).build();
        assertThat(winnerForThe(board), is(BLACK));
    }

    @Test public void
    return_winner_when_five_consecutive_stones_in_an_anti_diagonal() {
        Board board = aBoard().with(blackStones(
                                at(14, 0),
                                at(13, 1),
                                at(12, 2),
                                at(11, 3),
                                at(10, 4))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(9, 0),
                                at(8, 1),
                                at(7, 2),
                                at(6, 3),
                                at(5, 4))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(10, 10),
                                at(11, 11),
                                at(12, 12),
                                at(13, 13),
                                at(14, 14))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(0, 10),
                                at(1, 11),
                                at(2, 12),
                                at(3, 13),
                                at(4, 14))).build();
        assertThat(winnerForThe(board), is(BLACK));

        board = aBoard().with(blackStones(
                                at(12, 7),
                                at(11, 8),
                                at(10, 9),
                                at(9, 10),
                                at(8, 11))).build();
        assertThat(winnerForThe(board), is(BLACK));
    }

    private Player winnerForThe(Board board) {
        Optional<Player> winner = rules.winner(board);
        return (winner.isPresent()) ? winner.get() : NO_WINNER;
    }

}