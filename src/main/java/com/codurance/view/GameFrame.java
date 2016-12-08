package com.codurance.view;

import javax.swing.*;
import java.awt.*;

public class GameFrame {

    private static final Component SCREEN_CENTER = null;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 520;
    private static final String GAME_TITLE = "Gomoku";
    private JFrame frame;
    private JPanel mainPanel;

    public GameFrame(Header header, BoardView boardView) {
        initialiseFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(header);
        mainPanel.add(boardView);
        frame.add(mainPanel);
        boardView.addObserver(header);
    }

    private void initialiseFrame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle(GAME_TITLE);
        frame.setLocationRelativeTo(SCREEN_CENTER); // center of the screen
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setVisible() {
        frame.setVisible(true);
    }
}
