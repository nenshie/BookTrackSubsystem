package main;

import ui.MainForm;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm frame = new MainForm();
            frame.setVisible(true);
        });
    }



}
