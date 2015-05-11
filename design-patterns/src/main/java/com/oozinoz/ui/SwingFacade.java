package com.oozinoz.ui;

import java.awt.Component;

import javax.swing.JFrame;

public class SwingFacade {
	
    public static JFrame launch(Component c, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().add(c);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
