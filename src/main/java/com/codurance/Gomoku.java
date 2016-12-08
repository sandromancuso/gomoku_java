package com.codurance;

public class Gomoku {

    public static void main(String[] args) {
        Board board = new Board();

        Header header = new Header();
        BoardView boardView = new BoardView(board);

        GameFrame gameFrame = new GameFrame(header, boardView);

        gameFrame.setVisible();
    }

}