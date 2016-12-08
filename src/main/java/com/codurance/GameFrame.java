package com.codurance;

import javax.swing.*;
import java.awt.*;

class GameFrame {

    private static final Component SCREEN_CENTER = null;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 520;
    private static final String GAME_TITLE = "Gomoku";
    private JFrame frame;
    private JPanel mainPanel;

    GameFrame(JPanel header, JPanel board) {
        initialiseFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(header);
        mainPanel.add(board);
        frame.add(mainPanel);
    }

    private void initialiseFrame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle(GAME_TITLE);
        frame.setLocationRelativeTo(SCREEN_CENTER); // center of the screen
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void setVisible() {
        frame.setVisible(true);
    }
}
