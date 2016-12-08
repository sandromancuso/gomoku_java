package com.codurance.model;

import org.junit.Before;
import org.junit.Test;

import static com.codurance.model.Game.Player.BLACK;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameShould {

    public static final Board.Intersection INTERSECTION_1x1 = Board.intersection(1, 1).get();
    public static final Board.Intersection INTERSECTION_2x2 = Board.intersection(1, 1).get();

    private Game game;

    @Before
    public void initialise() {
        game = new Game(new Board());
    }

    @Test public void
    start_with_back_player_as_the_first_player() {
        Game game = new Game(new Board());

        assertThat(game.currentPlayer(), is(BLACK));
    }

    @Test public void
    switch_current_players_after_each_stone_is_placed() {
        assertThat(game.currentPlayer(), is(Game.Player.BLACK));

        game.placeStoneAt(INTERSECTION_1x1);
        assertThat(game.currentPlayer(), is(Game.Player.WHITE));

        game.placeStoneAt(INTERSECTION_2x2);
        assertThat(game.currentPlayer(), is(Game.Player.BLACK));
    }

}