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
        board.stones().forEach(s -> draw(g, s));
    }

    private void draw(Graphics g, Board.Stone stone) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        int xx = (stone.intersection().x() * squareSize) + squareSize;
        int yy = (stone.intersection().y() * squareSize) + squareSize;

//        Graphics2D g = (Graphics2D) getGraphics();
        int r = Math.round(squareSize / 2);
        xx = xx-(r/2);
        yy = yy-(r/2);
        g.fillOval(xx,yy,r,r);
    }

    private void drawStone(int x, int y) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        int intersectionX = Math.round((float)x / squareSize) - 1;
        int intersectionY = Math.round((float)y / squareSize) - 1;
        System.out.println("x = " + intersectionX + ", y = " + intersectionY);


        int xx = (intersectionX * squareSize) + squareSize;
        int yy = (intersectionY * squareSize) + squareSize;

        Graphics2D g = (Graphics2D) getGraphics();
        int r = Math.round(squareSize / 2);
        xx = xx-(r/2);
        yy = yy-(r/2);
        g.fillOval(xx,yy,r,r);
    }

    private void placeStone(int x, int y) {
        int squareSize = WIDTH / (Board.Y_INTERSECTIONS + 1);
        int intersectionX = Math.round((float)x / squareSize) - 1;
        int intersectionY = Math.round((float)y / squareSize) - 1;

        Optional<Board.Intersection> intersection = Board.intersection(intersectionX, intersectionY);
        intersection.ifPresent(board::placeStoneAt);
    }

    private class BoardMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            placeStone(e.getX(), e.getY());
            drawStone(e.getX(), e.getY());
        }

    }

}
