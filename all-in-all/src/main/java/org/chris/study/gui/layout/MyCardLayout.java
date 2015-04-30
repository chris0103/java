package org.chris.study.gui.layout;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyCardLayout {

	private static JFrame aWindow = new JFrame("This is a Card Layout");
	private static CardLayout card = new CardLayout(50, 50);
	
	public static void applyLayout() {
		Container content = aWindow.getContentPane();
		content.setLayout(card);
		JButton button = null;
		for (int i = 1; i <= 6; i++) {
			content.add(button = new JButton(" Press " + i), "Card" + i);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					card.next(aWindow.getContentPane());
				}
			});
		}
		card.show(content, "Card3");
	}
	
	public static void main(String[] args) {
		Toolkit theKit = aWindow.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		aWindow.setBounds(wndSize.width / 4, wndSize.height / 4, wndSize.width / 2, wndSize.height / 2);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applyLayout();
		aWindow.pack();
		aWindow.setVisible(true);
	}
}
