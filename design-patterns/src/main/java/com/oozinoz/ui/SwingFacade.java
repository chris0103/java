package com.oozinoz.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SwingFacade {
	
	public static Font getStandardFont() {
		return new Font("Dialog", Font.PLAIN, 18);
	}
	
	public static JPanel createTitledPanel(String title, JPanel content) {
		JPanel panel = new JPanel();
		panel.setBorder(createTitledBorder(title));
		panel.add(content);
		return panel;
	}
	
    public static JFrame launch(Component c, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().add(c);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
    
    private static Border createTitledBorder(String title) {
    	TitledBorder border = new TitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), title, TitledBorder.LEFT, TitledBorder.TOP);
    	border.setTitleColor(Color.black);
    	border.setTitleFont(getStandardFont());
    	return border;
    }
}
