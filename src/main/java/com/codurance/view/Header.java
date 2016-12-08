package com.codurance.view;

import com.codurance.model.Game;

import javax.swing.*;

import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Header extends JPanel {

    private final JLabel label = new JLabel("Some message");
    private Game game;

    public Header(Game game) {
        this.game = game;
        initialise();
    }

    private void initialise() {
        setBackground(BLACK);
        setSize(400, 100);
        add(Box.createVerticalStrut(80));
        label.setForeground(WHITE);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        label.setText(game.currentPlayer() + "'s turn");
    }
}
