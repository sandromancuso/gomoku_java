package com.codurance;

import javax.swing.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Header extends JPanel {

    private final JLabel label = new JLabel("Some message");

    public Header() {
        initialise();
    }

    private void initialise() {
        setBackground(BLACK);
        setSize(400, 100);
        add(Box.createVerticalStrut(80));
        label.setForeground(WHITE);
        add(label);
    }
}
