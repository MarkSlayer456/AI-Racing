package main.objects;

import java.awt.Rectangle;

public class FinishLine {

	public static final int DEFAULT_VALUE = 100000;
	public static final int DEFAULT_WIDTH = 25;
	public static final int DEFAULT_HEIGHT = 25;
	
	private int value;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public FinishLine(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.value = DEFAULT_VALUE;
	}

	public void checkIfHit(Car car) {
		Rectangle line = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int)this.getHeight());
		Rectangle carR = new Rectangle((int) car.getX(), (int) car.getY(), (int) car.getWidth(), (int) car.getHeight());
		if(line.intersects(carR)) {
			if(car.getGoals().isEmpty()) {
				car.setReachedFinish(true);
				car.killCar(); //TODO remove this
			}
		}
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
