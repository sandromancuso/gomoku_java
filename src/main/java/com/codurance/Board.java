package com.codurance;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;

public class Board extends JPanel {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final Color BOARD_COLOR = new Color(255, 188, 83);

    public Board() {
        setBackground(BOARD_COLOR);
        setForeground(BLACK);
        setBounds(0, 100, WIDTH, HEIGHT);
        setSize(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int squareSize = WIDTH / 16;
        for (int i = 1; i < 16; i++) {
            g.drawLine(squareSize, squareSize * i, WIDTH - squareSize, squareSize * i);
            g.drawLine(squareSize * i, squareSize, squareSize * i, HEIGHT - squareSize);
        }
    }
}
