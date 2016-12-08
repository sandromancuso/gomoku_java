package com.codurance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import static java.awt.Color.BLACK;

public class BoardView extends JPanel {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final Color BOARD_COLOR = new Color(255, 188, 83);
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        initialiseView();
    }

    private void initialiseView() {
        setBackground(BOARD_COLOR);
        setForeground(BLACK);
        setBounds(0, 100, WIDTH, HEIGHT);
        setSize(WIDTH, HEIGHT);
        addMouseListener(new BoardMouseAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawStones(g);
    }

    private void drawBoard(Graphics g) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        for (int i = 1; i < Board.Y_INTERSECTIONS + 1; i++) {
            g.drawLine(squareSize, squareSize * i, WIDTH - squareSize, squareSize * i);
            g.drawLine(squareSize * i, squareSize, squareSize * i, HEIGHT - squareSize);
        }
    }

    private void drawStones(Graphics g) {
        board.stones().forEach(s -> drawStone(g, s));
    }

    private void drawStone(Graphics g, Board.Stone stone) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        int x = (stone.intersection().x() * squareSize) + squareSize;
        int y = (stone.intersection().y() * squareSize) + squareSize;

        int r = Math.round(squareSize / 2);
        g.fillOval(x-(r/2), y-(r/2), r, r);
    }

    private void placeStone(int x, int y) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        int intersectionX = Math.round((float)x / squareSize) - 1;
        int intersectionY = Math.round((float)y / squareSize) - 1;

        Optional<Board.Intersection> intersection = Board.intersection(intersectionX, intersectionY);
        intersection.ifPresent(board::placeStoneAt);

        repaint();
    }

    private class BoardMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            placeStone(e.getX(), e.getY());
        }

    }

}
