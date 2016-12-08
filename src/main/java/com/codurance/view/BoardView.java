package com.codurance.view;

import com.codurance.model.Board;
import com.codurance.model.Game;
import com.codurance.model.Player;
import com.codurance.model.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import static java.awt.Color.BLACK;

public class BoardView extends JPanel {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final Color BOARD_COLOR = new Color(255, 188, 83);
    private static final int SQUARE_SIZE = WIDTH / (Board.Y_INTERSECTIONS + 1);

    private Game game;

    public BoardView(Game game) {
        this.game = game;
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
        for (int i = 1; i < Board.Y_INTERSECTIONS + 1; i++) {
            g.drawLine(SQUARE_SIZE, SQUARE_SIZE * i, WIDTH - SQUARE_SIZE, SQUARE_SIZE * i);
            g.drawLine(SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE * i, HEIGHT - SQUARE_SIZE);
        }
    }

    private void drawStones(Graphics g) {
        game.stones().forEach(s -> drawStone(g, s));
    }

    private void drawStone(Graphics g, Stone stone) {
        int x = (stone.intersection().x() * SQUARE_SIZE) + SQUARE_SIZE;
        int y = (stone.intersection().y() * SQUARE_SIZE) + SQUARE_SIZE;

        Color color = (stone.player() == Player.BLACK) ? Color.BLACK : Color.WHITE;
        setForeground(color);

        g.setColor(color);
        int r = Math.round(SQUARE_SIZE / 2);
        g.fillOval(x-(r/2), y-(r/2), r, r);

        setForeground(Color.BLACK);
    }

    private void placeStone(int x, int y) {
        int intersectionX = Math.round((float)x / SQUARE_SIZE) - 1;
        int intersectionY = Math.round((float)y / SQUARE_SIZE) - 1;

        Optional<Board.Intersection> intersection = Board.intersection(intersectionX, intersectionY);
        intersection.ifPresent(game::placeStoneAt);

        repaint();
    }

    private class BoardMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            placeStone(e.getX(), e.getY());
        }

    }

}
