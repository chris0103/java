package org.chirs.study.gui.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MyBoxLayout {

	private static JFrame aWindow = new JFrame("This is a Box Layout");
	
	public static void applyLayout() {
		// left box
		Box left = Box.createVerticalBox();
		left.add(Box.createVerticalStrut(30));
		ButtonGroup radioGroup = new ButtonGroup();
		JRadioButton rbutton;
		radioGroup.add(rbutton = new JRadioButton("Red"));
		left.add(rbutton);
		left.add(Box.createVerticalStrut(30));
		radioGroup.add(rbutton = new JRadioButton("Green"));
		left.add(rbutton);
		left.add(Box.createVerticalStrut(30));
		radioGroup.add(rbutton = new JRadioButton("Blue"));
		left.add(rbutton);
		left.add(Box.createVerticalStrut(30));
		radioGroup.add(rbutton = new JRadioButton("Yellow"));
		left.add(rbutton);
		left.add(Box.createGlue());
		
		// right box
		Box right = Box.createVerticalBox();
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Dashed"));
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Thick"));
		right.add(Box.createVerticalStrut(30));
		right.add(new JCheckBox("Rounded"));
		right.add(Box.createGlue());
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(new TitledBorder(new EtchedBorder(), "Line Color"));
		leftPanel.add(left, BorderLayout.CENTER);
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(new TitledBorder(new EtchedBorder(), "Line Properties"));
		rightPanel.add(right, BorderLayout.CENTER);
		
		// top row to hold left and right boxes
		Box top = Box.createHorizontalBox();
		top.add(leftPanel);
		top.add(Box.createHorizontalStrut(5));
		top.add(rightPanel);
		
		// bottom row of buttons
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1), BorderFactory.createBevelBorder(BevelBorder.RAISED)));
		Border edge = BorderFactory.createRaisedBevelBorder();
		JButton button;
		Dimension size = new Dimension(80, 20);
		bottomPanel.add(button = new JButton("Defaults"));
		button.setBorder(edge);
		button.setPreferredSize(size);
		bottomPanel.add(button = new JButton("OK"));
		button.setBorder(edge);
		button.setPreferredSize(size);
		bottomPanel.add(button = new JButton("Cancel"));
		button.setBorder(edge);
		button.setPreferredSize(size);
		
		// add top and bottom panel to content pane
		Container content = aWindow.getContentPane();
		BoxLayout box = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(box);
		content.add(top);
		content.add(bottomPanel, BorderLayout.SOUTH);
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
