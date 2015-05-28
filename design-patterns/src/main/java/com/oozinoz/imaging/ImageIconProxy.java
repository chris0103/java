package com.oozinoz.imaging;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageIconProxy extends ImageIcon implements Runnable {

	private static final ImageIcon ABSENT = new ImageIcon(ClassLoader.getSystemResource("images/absent.jpg"));
	private static final ImageIcon LOADING = new ImageIcon(ClassLoader.getSystemResource("images/loading.jpg"));
	
	private String filename;
	private JFrame callbackFrame;
	private ImageIcon current = ABSENT;
	
	public ImageIconProxy(String filename) {
		super(ABSENT.getImage());
		this.filename = filename;
	}
	
	public ImageIconProxy(JFrame callbackFrame) {
		this.callbackFrame = callbackFrame;
		current = LOADING;
		callbackFrame.repaint();
		new Thread(this).start();
	}
	
	public void run() {
		current = new ImageIcon(ClassLoader.getSystemResource(filename));
		callbackFrame.pack();
	}

	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		current.paintIcon(c, g, x, y);
	}
	
	public int getIconHeight() {
		return current.getIconHeight(); 
	}
	
	public int getIconWidth() {
		return current.getIconWidth();
	}
}
