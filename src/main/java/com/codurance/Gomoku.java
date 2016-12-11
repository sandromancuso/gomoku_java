package com.codurance;

import com.codurance.model.Board;
import com.codurance.model.Game;
import com.codurance.model.Rules;
import com.codurance.view.BoardView;
import com.codurance.view.GameFrame;
import com.codurance.view.Header;

public class Gomoku {

    public static void main(String[] args) {
        Rules rules = new Rules();
        Board board = new Board();
        Game game = new Game(board, rules);

        Header header = new Header(game);
        BoardView boardView = new BoardView(game);
        GameFrame gameFrame = new GameFrame(header, boardView);

        gameFrame.setVisible();
    }

}