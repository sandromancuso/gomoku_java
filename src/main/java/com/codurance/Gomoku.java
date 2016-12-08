package com.codurance;

public class Gomoku {

    public static void main(String[] args) {
        Header header = new Header();
        Board board = new Board();

        GameFrame gameFrame = new GameFrame(header, board);

        gameFrame.setVisible();
    }

}