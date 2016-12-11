package com.codurance.view;

import com.codurance.model.Game;
import com.codurance.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Header extends JPanel implements Observer {

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
        Optional<Player> winner = game.winner();
        if (!winner.isPresent()) {
            label.setText(game.currentPlayer() + "'s turn");
        }
        winner.ifPresent(player -> label.setText(player + " WON!!!"));
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
