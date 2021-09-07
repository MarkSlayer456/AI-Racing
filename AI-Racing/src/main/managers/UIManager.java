package main.managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class UIManager {

	private JFrame frame;
	private Graphics2D graphics;
	private BufferStrategy buff;
	
	public UIManager(Dimension size) {
		frame = new JFrame();
		frame.setSize(size);
		frame.setLocation(500, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setupBuff() {
		if(this.buff == null) { this.frame.createBufferStrategy(4); }
		this.buff = this.frame.getBufferStrategy();
		this.graphics = (Graphics2D) this.buff.getDrawGraphics();
	}
	
	public void disposeAndShow() {
		this.graphics.dispose();
		this.buff.show();
	}
	
	
	public void draw() {
		setupBuff();
		//---------------------------------------------------------------
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, 800, 600);
		graphics.setColor(Color.RED);
		
		//--------------------------------------------------------------
		disposeAndShow();
	}
	
}
