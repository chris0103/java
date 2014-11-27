package org.chirs.study.gui.layout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFlowLayout {

	private static JFrame aWindow = new JFrame("This is a Flow Layout");
	
	public static void applyLayout() {
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		Container content = aWindow.getContentPane();
		content.setLayout(flow);
		for (int i = 0; i < 6; i++) {
			content.add(new JButton("Press " + i));
		}
	}
	
	public static void setSpacing() {
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 30);
		Container content = aWindow.getContentPane();
		content.setLayout(flow);
		for (int i = 0; i < 6; i++) {
			content.add(new JButton("Press " + i));
		}
	}
	
	public static void main(String[] args) {
		Toolkit theKit = aWindow.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		aWindow.setBounds(wndSize.width / 4, wndSize.height / 4, wndSize.width / 2, wndSize.height / 2);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSpacing();
		aWindow.setVisible(true);
	}
}
