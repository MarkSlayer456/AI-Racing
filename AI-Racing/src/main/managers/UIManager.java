package main.managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import main.Game;
import main.Track;
import main.objects.Car;
import main.objects.CheckPoint;
import main.objects.FinishLine;
import main.objects.Wall;

public class UIManager {

	public static final double DEFAULT_WIDTH = 800;
	public static final double DEFAULT_HEIGHT = 600;
	
	public static double WIDTH_SCALE = 1;
	public static double HEIGHT_SCALE = 1;
	
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
		WIDTH_SCALE = ((double) this.frame.getWidth() / DEFAULT_WIDTH);
		HEIGHT_SCALE = ((double) this.frame.getHeight() / DEFAULT_HEIGHT);
		AffineTransform at = new AffineTransform(1.0, 0, 0, 1.0, 0, 0);
		at.scale(WIDTH_SCALE, HEIGHT_SCALE);
		graphics.setTransform(at);
		
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, 800, 600);
		Track track = Game.currentTrack;
		graphics.setColor(Color.blue);
		for(int i = 0; i < track.getCheckPoints().length; i++) {
			CheckPoint point = track.getCheckPoints()[i];
			if(point == null) break;
			Rectangle2D rect = new Rectangle2D.Double(point.getX(), point.getY(), point.getWidth(), point.getHeight());
			graphics.fill(rect);
		}

		graphics.setColor(Color.CYAN);
		Car[] cars = Game.pop.getCars();
		for(int i = 0; i < cars.length; i++) {
			Rectangle2D rect = new Rectangle2D.Double(cars[i].getX(), cars[i].getY(), cars[i].getWidth(), cars[i].getHeight());
			graphics.fill(rect);
		}
		
		graphics.setColor(Color.red);
		for(int i = 0; i < track.getWalls().length; i++) {
			Wall wall = track.getWalls()[i];
			if(wall == null) break;
			Rectangle2D rect = new Rectangle2D.Double(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			graphics.fill(rect);
		}
		
		graphics.setColor(Color.magenta);
		FinishLine line = track.getFinishLine();
		if(line != null) {
			Rectangle2D rect = new Rectangle2D.Double(line.getX(), line.getY(), line.getWidth(), line.getHeight());
			graphics.fill(rect);
		}
		// TODO line could be null
		
		
		
		
		
		
		//--------------------------------------------------------------
		disposeAndShow();
	}
	
}
