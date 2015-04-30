package org.chris.study.gui.layout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class MyGridBagLayout {

	private static JFrame aWindow = new JFrame("This is a Grid Bag Layout");
	
	public static void applyLayout() {
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		aWindow.getContentPane().setLayout(gridBag);
		
		// set constraints and add first button
		constraints.weightx = constraints.weighty = 10.0;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.ipadx = 30;
		constraints.ipady = 10;
		addButton(" Press ", constraints, gridBag);
		
		// set constraints and add second button
		constraints.weightx = 5.0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.ipadx = constraints.ipady = 0;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.insets = new Insets(10, 30, 10, 20);
		constraints.gridheight = 2;
		addButton("GO", constraints, gridBag);
		
		// set constraints and add third button
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.ipadx = 30;
		constraints.ipady = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		addButton("Push", constraints, gridBag);
	}
	
	private static void addButton(String label, GridBagConstraints constraints, GridBagLayout layout) {
		Border edge = BorderFactory.createRaisedBevelBorder();
		JButton button = new JButton(label);
		button.setBorder(edge);
		layout.setConstraints(button, constraints);
		aWindow.getContentPane().add(button);
	}
	
	public static void main(String[] args) {
		Toolkit theKit = aWindow.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		aWindow.setBounds(wndSize.width / 4, wndSize.height / 4, wndSize.width / 2, wndSize.height / 2);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applyLayout();
		aWindow.setVisible(true);
	}
}
