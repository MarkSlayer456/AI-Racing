package main.objects;

import java.awt.Rectangle;

public class Wall {

	public static final double DEFAULT_WIDTH = 25;
	public static final double DEFAULT_HEIGHT = 25;
	
	private double x;
	private double y;
	private double height;
	private double width;
	
	public Wall(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void checkIfHit(Car car) {
		Rectangle wall = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int)this.getHeight());
		Rectangle carR = new Rectangle((int) car.getX(), (int) car.getY(), (int) car.getWidth(), (int) car.getHeight());
		if(wall.intersects(carR)) {
			car.killCar();
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	
}
