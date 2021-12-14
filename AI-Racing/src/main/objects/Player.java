package main.objects;

import main.managers.InputManager;

public class Player {

	private Car car;
	private InputManager input;
	
	public Player(Car car, InputManager input) {
		this.car = car;
		this.input = input;
	
	}
	
	public void doLogic() {
		move();
	}
	
	public void move() {
		if((input.getKeysDown().contains(38)) || (input.getKeysDown().contains(87))) {
			// -1 y
		}
		if ((input.getKeysDown().contains(40)) || (input.getKeysDown().contains(83))) {
			// +1 y
		}
		if ((input.getKeysDown().contains(39)) || (input.getKeysDown().contains(68))) {
			// +1 x
		}
		if ((input.getKeysDown().contains(37)) || (input.getKeysDown().contains(65))) {
			// -1 x
		}
	}
	
	public Car getCar() {
		return this.car;
	}
	
}
