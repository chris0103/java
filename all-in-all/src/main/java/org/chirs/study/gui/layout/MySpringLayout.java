package org.chirs.study.gui.layout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class MySpringLayout {

	private static JFrame aWindow = new JFrame("This is a Spring Layout");
	
	public static void applyLayout() {
		SpringLayout layout = new SpringLayout();
		Container content = aWindow.getContentPane();
		content.setLayout(layout);
		
		JButton[] buttons = new JButton[6];
		SpringLayout.Constraints constraints = null;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("Press " + (i + 1));
			content.add(buttons[i]);
		}
		
		Spring xSpring = Spring.constant(5, 15, 25);
		Spring ySpring = Spring.constant(10, 30, 50);
		constraints = layout.getConstraints(buttons[0]);
		constraints.setX(xSpring);
		constraints.setY(ySpring);
		
		for (int i = 1; i < buttons.length; i++) {
			constraints = layout.getConstraints(buttons[i]);
			layout.putConstraint(SpringLayout.WEST, buttons[i], xSpring, SpringLayout.EAST, buttons[i - 1]);
			layout.putConstraint(SpringLayout.NORTH, buttons[i], ySpring, SpringLayout.SOUTH, buttons[i - 1]);
		}
		
		SpringLayout.Constraints containerConstraints = layout.getConstraints(content);
		containerConstraints.setConstraint(SpringLayout.EAST, Spring.sum(constraints.getConstraint(SpringLayout.EAST), Spring.constant(15)));
		containerConstraints.setConstraint(SpringLayout.SOUTH, Spring.sum(constraints.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
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
