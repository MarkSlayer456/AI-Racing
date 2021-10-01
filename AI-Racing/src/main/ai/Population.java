package main.ai;

import main.objects.Car;
import main.objects.CheckPoint;

public class Population {

	private final float CHANCE_OF_DUMB_CHILD = 100f;
	
	private Car[] cars;
	private int size;
	private int alive;
	
	public Population(int size, CheckPoint[] points) {
		this.size = size;
		cars = new Car[size];
		for(int i = 0; i < size; i++) {
			cars[i] = new Car();
			for(int j = 0; j < points.length; j++) {
				cars[i].addGoal(points[j]);
			}
		}
		this.alive = size;
	}
	
	
	public Car[] getCars() {
		return this.cars;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getAlive() {
		return this.alive;
	}
	
	public void decreaseAlive(int amount) {
		this.alive -= amount;
	}
}
