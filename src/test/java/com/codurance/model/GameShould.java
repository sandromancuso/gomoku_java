package com.codurance.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.codurance.model.Player.BLACK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GameShould {

    private static final Board.Intersection INTERSECTION_1x1 = Board.intersection(1, 1).get();
    private static final Board.Intersection INTERSECTION_2x2 = Board.intersection(2, 2).get();

    @Mock
    private Rules rules;

    private Board board;

    private Game game;

    @Before
    public void initialise() {
        board = new Board();
        game = new Game(board, rules);
    }

    @Test public void
    start_with_back_player_as_the_first_player() {
        assertThat(game.currentPlayer(), is(BLACK));
    }

    @Test public void
    switch_current_players_after_each_stone_is_placed() {
        assertThat(game.currentPlayer(), is(Player.BLACK));

        game.placeStoneAt(INTERSECTION_1x1);
        assertThat(game.currentPlayer(), is(Player.WHITE));

        game.placeStoneAt(INTERSECTION_2x2);
        assertThat(game.currentPlayer(), is(Player.BLACK));
    }

    @Test public void
    place_a_stone_for_the_current_player() {
        Stone blackStone = new Stone(INTERSECTION_1x1, Player.BLACK);
        Stone whiteStone = new Stone(INTERSECTION_2x2, Player.WHITE);

        game.placeStoneAt(INTERSECTION_1x1);
        game.placeStoneAt(INTERSECTION_2x2);

        assertThat(game.stones().contains(blackStone), is(true));
        assertThat(game.stones().contains(whiteStone), is(true));
    }

    @Test public void
    not_switch_players_when_stone_is_not_placed() {
        game.placeStoneAt(INTERSECTION_1x1);
        game.placeStoneAt(INTERSECTION_1x1);

        assertThat(game.currentPlayer(), is(Player.WHITE));
    }

    @Test public void
    return_no_winner_if_there_are_no_five_stones_in_a_row_for_any_player() {
        given(rules.winner(board)).willReturn(Optional.empty());

        assertThat(game.winner().isPresent(), is(false));
    }

    @Test public void
    return_winner_if_there_are_five_stones_in_a_row_for_any_player() {
        given(rules.winner(board)).willReturn(Optional.of(Player.BLACK));

        assertThat(game.winner().get(), is(Player.BLACK));
    }

}