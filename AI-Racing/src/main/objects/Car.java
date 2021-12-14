package main.objects;

import java.util.ArrayList;

import main.Game;
import main.ai.Brain;
import main.physics.PVector;

public class Car {
	
	public static Car top;
	
	private boolean alive;
	private int x;
	private int y;
	private Brain brain;
	private float fitness;
	private int stepsTaken;
	private PVector state;
	private int width;
	private int height;
	private int runs;
	
	private ArrayList<CheckPoint> goals;
	private int currentGoal; // total checkpoints reached
//	private CheckPoint current; // current checkpoint
//	private FinishLine finish; // what it's trying to accomplish in the long run
	private boolean reachedFinish;
	private boolean isPlayer;
	
	public Car(boolean isPlayer) {
		this.state = new PVector();
		this.x = 50;
		this.y = 75;
		this.alive = true;
		this.setHeight(25);
		this.setWidth(25);	
		this.reachedFinish = false;
		if(isPlayer) {
			this.brain = null;
		} else {
			this.brain = new Brain(Brain.BRAIN_SIZE);
			this.currentGoal = 0;
			this.runs = 0;
			this.goals = new ArrayList<CheckPoint>();
		}
		
		//TODO checkpoints and finish line
	}
	
	public void move() {
		if(this.alive) {
			if(this.stepsTaken >= this.getBrain().getSize()) {
				this.stepsTaken--;
				this.killCar();
				System.out.println("death to steps!");
			} else {
				this.state.add(this.getBrain().getDirections(this.stepsTaken));

				double x = Math.toRadians(Math.cos(Math.toRadians(this.state.getDir()))) * this.state.getMag();
				double y = Math.toRadians(Math.sin(Math.toRadians(this.state.getDir()))) * this.state.getMag();
				for(int i = 0; i < x; i++) {
					this.setX(this.getX() + 1);
				}
				for(int i = 0; i > x; i--) {
					this.setX(this.getX() - 1);
				}
				for(int i = 0; i < y; i++) {
					this.setY(this.getY() + 1);
				}
				for(int i = 0; i > y; i--) {
					this.setY(this.getY() - 1);
				}
				this.stepsTaken++;
			}
		}
	}
	
	public void calculateFitness() {
		float score = 10000;
		if(!reachedFinish) {
			int add = this.stepsTaken * 3;
			score += add;
		} else {
			int sub = this.stepsTaken * 3;
			score -= sub;
		}
		score += CheckPoint.DEFAULT_VALUE * this.currentGoal;
		if(reachedFinish) {
			score += FinishLine.DEFAULT_VALUE;
		}
		this.fitness = score;
	}
	
	public float getFitness() {
		return this.fitness;
	}

	public void killCar() {
		this.alive = false;
		this.calculateFitness();
		if(this.stepsTaken > Brain.top_size) { //TODO this is bad
			Brain.top_size = this.stepsTaken;
		}
		if(top != null) {
			if(top.getFitness() < this.getFitness()) {
				top.setBrain(this.getBrain().clone());
				top.setFitness(this.getFitness());
			}
		} else {
			top = new Car(false);
			top.setBrain(this.getBrain().clone());
			top.setFitness(this.getFitness());
		}
		
		if(top.getFitness() * .75 < this.getFitness()) {
			for(int i = 0; i < this.getBrain().getSize()*0.25; i++) {
				if(this.getBrain().getDirections(i).getDir() == PVector.DIRECTION_LEFT) {
					this.getBrain().increaseLeft(i, 0.25);
				} else if(this.getBrain().getDirections(i).getDir() == PVector.DIRECTION_RIGHT) {
					this.getBrain().increaseRight(i, 0.25);
				} else if(this.getBrain().getDirections(i).getDir() == PVector.DIRECTION_FORWARD) {
					this.getBrain().increaseForward(i, 0.25);
				}
			}
		}
		
		
		for(int i = 6; i >= 0; i--) { // 360 / 15 = 24
			double decModify = 5;
			if(this.getBrain().getDirections(this.stepsTaken - i).getDir() == PVector.DIRECTION_LEFT) {
				this.getBrain().decreaseLeft(this.stepsTaken - i, (this.getFitness() / top.getFitness()) * decModify);
			} else if(this.getBrain().getDirections(this.stepsTaken - i).getDir() == PVector.DIRECTION_RIGHT) {
				this.getBrain().decreaseRight(this.stepsTaken - i, (this.getFitness() / top.getFitness()) * decModify);
			} else if(this.getBrain().getDirections(this.stepsTaken - i).getDir() == PVector.DIRECTION_FORWARD) {
				this.getBrain().decreaseForward(this.stepsTaken - i, (this.getFitness() / top.getFitness()) * decModify);
			} else {
				System.out.println("unknown move detected");
			}
			this.getBrain().mutate_move(this.stepsTaken - i);
		}
		this.x = 50;
		this.y = 75;
		this.state = new PVector();
		this.alive = true;
		this.currentGoal = 0;
		this.fitness = 0;
		this.reachedFinish = false;
		for(CheckPoint p : Game.currentTrack.getCheckPoints()) {
			if(!this.getGoals().contains(p))
				this.addGoal(p);
		}
		this.runs++;
		if(this.runs > 5) {
			this.setBrain(top.getBrain().clone().mutate(this.stepsTaken).clone());
			this.runs = 0;
		}
		this.stepsTaken = 0;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
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

	public Brain getBrain() {
		return brain;
	}

	public void setBrain(Brain brain) {
		this.brain = brain;
	}

	public int getStepsTaken() {
		return stepsTaken;
	}

	public void setStepsTaken(int stepsTaken) {
		this.stepsTaken = stepsTaken;
	}

	public PVector getState() {
		return state;
	}

	public void setState(PVector state) {
		this.state = state;
	}

//	public CheckPoint getCurrent() {
//		return current;
//	}

	public void incCurrentGoal(int amount) {
		this.currentGoal += amount;
	}
	
//	public void setCurrent(CheckPoint current) {
//		this.current = current;
//	}

	public int getCurrentGoal() {
		return currentGoal;
	}

	public void setCheckPointCount(int currentGoal) {
		this.currentGoal = currentGoal;
	}
	
	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addGoal(CheckPoint point) {
		this.goals.add(point);
	}
	
	public void removeGoal(CheckPoint point) {
		this.goals.remove(point);
	}
	
	public ArrayList<CheckPoint> getGoals() {
		return this.goals;
	}

	public boolean isReachedFinish() {
		return reachedFinish;
	}

	public void setReachedFinish(boolean reachedFinish) {
		this.reachedFinish = reachedFinish;
	}
	
}
