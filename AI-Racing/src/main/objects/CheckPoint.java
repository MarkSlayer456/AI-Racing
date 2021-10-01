package main.objects;

import java.awt.Rectangle;

import main.physics.PVector;

public class CheckPoint {
	
	public static final int DEFAULT_VALUE  = 10000;
	public static final int DEFAULT_WIDTH = 25;
	public static final int DEFAULT_HEIGHT = 25;
	
	private int value;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public CheckPoint(int x, int y) {
		this.value = DEFAULT_VALUE;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.x = x;
		this.y = y;
	}
	
	public void checkIfHit(Car car) {
		Rectangle point = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int)this.getHeight());
		Rectangle carR = new Rectangle((int) car.getX(), (int) car.getY(), (int) car.getWidth(), (int) car.getHeight());
		if(point.intersects(carR)) {
			if(car.getGoals().contains(this)) {
				car.incCurrentGoal(1);
				car.removeGoal(this);
//				for(int i = 3; i >= 0; i--) {
//					double decModify = 2;
//					if(car.getBrain().getDirections(car.getStepsTaken() - i).getDir() == PVector.DIRECTION_LEFT) {
//						car.getBrain().increaseLeft(car.getStepsTaken() - i, decModify);
//					} else if(car.getBrain().getDirections(car.getStepsTaken() - i).getDir() == PVector.DIRECTION_RIGHT) {
//						car.getBrain().increaseRight(car.getStepsTaken() - i, decModify);
//					} else if(car.getBrain().getDirections(car.getStepsTaken() - i).getDir() == PVector.DIRECTION_FORWARD) {
//						car.getBrain().increaseForward(car.getStepsTaken()- i, decModify);
//					} else {
//						System.out.println("unknown move detected");
//					}
//					car.getBrain().mutate_move(car.getStepsTaken() - i);
//				}
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
