package com.codurance;

import javax.swing.*;

import static java.awt.Color.YELLOW;

public class Header extends JPanel {

    private final JLabel label = new JLabel("Some message");

    public Header() {
        initialise();
    }

    private void initialise() {
        setBackground(YELLOW);
        setSize(400, 100);
        add(Box.createVerticalStrut(80));
        add(label);
    }
}
