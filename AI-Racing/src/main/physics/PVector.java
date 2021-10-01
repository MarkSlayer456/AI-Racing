package main.physics;

public class PVector {

	public static final int LIMIT_DEFAULT_VALUE = 25;
	public static final int ANGLE_CHANGE_LIMIT = 25;
	public static final int MAG_CHANGE_LIMIT = 2;
	
	public static final int MAG_FORWARD = 1;
	public static final int DIRECTION_FORWARD = 0;
	public static final int DIRECTION_LEFT = -15;
	public static final int DIRECTION_RIGHT = 15;

	public static final int MAX_VEL = 3;
	
	private double magnitude;
	private int direction;
	private Movements movement; 
	
	public PVector(Movements movement) {
		if(movement == null) movement = Movements.FORWARD;
		switch(movement) {
			case FORWARD:
				this.magnitude = MAG_FORWARD;
				this.direction = DIRECTION_FORWARD;
				break;
			case LEFT:
				this.magnitude = MAG_FORWARD;
				this.direction = DIRECTION_LEFT;
				break;
			case RIGHT:
				this.magnitude = MAG_FORWARD;
				this.direction = DIRECTION_RIGHT;
				break;
			default:
				this.magnitude = MAG_FORWARD;
				this.direction = DIRECTION_FORWARD;
				break;
		
		}
		this.movement = movement;
	}
	
	public PVector() {
		this.magnitude = 0;
		this.direction = 0;
	}
	
	public void add(PVector addVector) {
		this.magnitude += addVector.getMag();
		if(this.magnitude > MAX_VEL) {
			this.magnitude = MAX_VEL;
		}
		if(LIMIT_DEFAULT_VALUE >= this.magnitude) {
			this.magnitude = LIMIT_DEFAULT_VALUE;
		}
		this.direction += addVector.direction;
		this.direction %= 360;
	}
	
	public double getDir() {
		return this.direction;
	}
	
	public double getMag() {
		return this.magnitude;
	}
	
	public void reset() {
		this.magnitude = 0;
		this.direction = 0;
	}
		
}
