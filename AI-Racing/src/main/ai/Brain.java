package main.ai;

import java.util.Random;

import main.physics.Movements;
import main.physics.PVector;

public class Brain {

	public static final float LATE_CHANCE_TO_MUTATE = 0.001f;
	public static final float MID_CHANCE_TO_MUTATE = 0.01f;
	public static final float EARLY_CHANCE_TO_MUTATE = 0.02f;
	public static final float LATE_MUTATE_PERCENT = 0.75f;
	public static final float MID_MUTATE_PERCENT = 0.50f;
	public static final float EARLY_MUTATE_PERCENT = 0.25f;
	
	/**
	 *  idea, check rotation on death, instead of moves
	 *  would need to use every 15 degrees, so the array would be 24 long instead of just 3, 
	 *  and that's for each move
	 */
	
	public static final int BRAIN_SIZE = 3500;
	public static int top_size;
	
	public static final int TOTAL_SCORE = 100;
	public static final int TOTAL_DIFFERENT_MOVEMENTS = 3; // 0 = forward, 1 = left, 2 = right, 3 = mutate_chance
	
	public static double[][] scores;
	
	private PVector[] directions;
	private final int size;
	
	public Brain(int size) {
		this.size = size;
		top_size = 0;
		this.directions = new PVector[size];
		randomizer();
	}
	
	public static void init(int size) {
		scores = new double[size][TOTAL_DIFFERENT_MOVEMENTS];
		for(int i = 0; i < size; i++) {
			scores[i][0] = TOTAL_SCORE/TOTAL_DIFFERENT_MOVEMENTS;
			scores[i][1] = TOTAL_SCORE/TOTAL_DIFFERENT_MOVEMENTS;
			scores[i][2] = TOTAL_SCORE/TOTAL_DIFFERENT_MOVEMENTS;
		}
	}
	
	public void randomizer() {
		for(int i = 0; i < this.size; i++) {
			Movements move = null;
			double ran = Math.random();
			double forward = scores[i][0];
			double left = scores[i][1] + forward;
			double right = scores[i][2] + left;
			if((Math.floor(ran * TOTAL_SCORE)) <= forward) {
				move = Movements.FORWARD;
			} else if(Math.floor(ran * TOTAL_SCORE) <= left) {
				move = Movements.LEFT;
			} else if(Math.floor(ran * TOTAL_SCORE) <= right) {
				move = Movements.RIGHT;
			} else {
				move = Movements.FORWARD;
			}
			
			directions[i] = new PVector(move);
		}
	}
	
	public Brain clone() {
		Brain brain = new Brain(this.size);
		for(int i = 0; i < size; i++) {
			brain.directions[i] = this.getDirections(i);
		}
		return brain;
	}
	
	
	public void mutate_move(int step) {
		Movements move = null;
		double r1 = Math.random();
		double forward = scores[step][0];
		double left = scores[step][1] + forward;
		double right = scores[step][2] + left;
		if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
			move = Movements.FORWARD;
		} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
			move = Movements.LEFT;
		} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
			move = Movements.RIGHT;
		} else {
			move = Movements.FORWARD;
		}
//		System.out.println(move + " | " + step + " | " + scores[step][0] + "," + scores[step][1] + "," + scores[step][2]); useful for debugging
		this.directions[step] = new PVector(move);
	}
	
	public Brain mutate(int stepsTaken) {
		Random ran = new Random();
		for(int i = 0; i < this.size; i++) {
			float r = ran.nextFloat();
			
			if(r < LATE_CHANCE_TO_MUTATE) {
//			if(r < i / stepsTaken) {
				Movements move = null;
				double r1 = Math.random();
				double forward = scores[i][0];
				double left = scores[i][1] + forward;
				double right = scores[i][2] + left;
				if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
					move = Movements.FORWARD;
				} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
					move = Movements.LEFT;
				} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
					move = Movements.RIGHT;
				}
				this.directions[i] = new PVector(move);
			}
			
//			if(stepsTaken >= top_size * LATE_MUTATE_PERCENT) {
//				if(r < LATE_CHANCE_TO_MUTATE) {
//					Movements move = null;
//					double r1 = Math.random();
//					double forward = scores[i][0];
//					double left = scores[i][1] + forward;
//					double right = scores[i][2] + left;
//					if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
//						move = Movements.FORWARD;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
//						move = Movements.LEFT;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
//						move = Movements.RIGHT;
//					}
//					this.directions[i] = new PVector(move);
//				}
//			} else if(stepsTaken >= top_size * MID_MUTATE_PERCENT) {
//				if(r < MID_CHANCE_TO_MUTATE) {
//					Movements move = null;
//					double r1 = Math.random();
//					double forward = scores[i][0];
//					double left = scores[i][1] + forward;
//					double right = scores[i][2] + left;
//					if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
//						move = Movements.FORWARD;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
//						move = Movements.LEFT;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
//						move = Movements.RIGHT;
//					}
//					this.directions[i] = new PVector(move);
//				}
//			} else if(stepsTaken >= top_size * EARLY_MUTATE_PERCENT) {
//				if(r < EARLY_CHANCE_TO_MUTATE) {
//					Movements move = null;
//					double r1 = Math.random();
//					double forward = scores[i][0];
//					double left = scores[i][1] + forward;
//					double right = scores[i][2] + left;
//					if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
//						move = Movements.FORWARD;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
//						move = Movements.LEFT;
//					} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
//						move = Movements.RIGHT;
//					}
//					this.directions[i] = new PVector(move);
//				}
//			} else {
//				Movements move = null;
//				double r1 = Math.random();
//				double forward = scores[i][0];
//				double left = scores[i][1] + forward;
//				double right = scores[i][2] + left;
//				if(Math.floor(r1 * TOTAL_SCORE) <= forward) {
//					move = Movements.FORWARD;
//				} else if(Math.floor(r1 * TOTAL_SCORE) <= left) {
//					move = Movements.LEFT;
//				} else if(Math.floor(r1 * TOTAL_SCORE) <= right) {
//					move = Movements.RIGHT;
//				}
//				this.directions[i] = new PVector(move);
//			}
		}
		return this;
	}
	
	
	public PVector getDirections(int i) {
		return this.directions[i];
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	//TODO these need values attached to make them more versitile
	public void decreaseLeft(int step, double amount) {
		double half = amount/2;
		if(scores[step][1] - amount < 0 || scores[step][2] + half > 100 || scores[step][0] + half > 100) {
			return;
		}
		scores[step][1] -= amount;
		scores[step][0] += half;
		scores[step][2] += half;	
	}
	
	public void decreaseRight(int step, double amount) {
		double half = amount/2;
		if(scores[step][2] - amount < 0 || scores[step][1] + half > 100 || scores[step][0] + half > 100) {
			return;
		}
		scores[step][2] -= amount;
		scores[step][0] += half;
		scores[step][1] += half;
	}
	
	public void decreaseForward(int step, double amount) {
		double half = amount/2;
		if(scores[step][0] - amount < 0 || scores[step][1] + half > 100 || scores[step][2] + half > 100) {
			return;
		}
		scores[step][0] -= amount;
		scores[step][1] += half;
		scores[step][2] += half;
	}
	
	public void increaseForward(int step, double amount) {
		double half = amount/2;
		if(scores[step][0] + amount > 100 || scores[step][1] - half < 0 || scores[step][2] - half < 0) {
			return;
		}
		scores[step][0] += amount;
		scores[step][1] -= half;
		scores[step][2] -= half;
	}
	public void increaseLeft(int step, double amount) {
		double half = amount/2;
		if(scores[step][1] + amount > 100 || scores[step][0] - half < 0 || scores[step][2] - half < 0) {
			return;
		}
		scores[step][1] += amount;
		scores[step][0] -= half;
		scores[step][2] -= half;
	}
	public void increaseRight(int step, double amount) {
		double half = amount/2;
		if(scores[step][2] + amount > 100 || scores[step][1] - half < 0 || scores[step][0] - half < 0) {
			return;
		}
		scores[step][2] += amount;
		scores[step][1] -= half;
		scores[step][0] -= half;
	}
	
	public boolean forwardAtOne(int step) {
		if(scores[step][0] < 2) return true;
		return false;
	}
	
	public boolean leftAtOne(int step) {
		if(scores[step][1] < 2) return true;
		return false;
	}
	
	public boolean rightAtOne(int step) {
		if(scores[step][2] < 2) return true;
		return false;
	}
	
	public boolean forwardToHigh(int step) {
		if(scores[step][0] > 50) return true;
		return false;
	}
	
	public boolean leftToHigh(int step) {
		if(scores[step][1] > 50) return true;
		return false;
	}
	
	public boolean rightToHigh(int step) {
		if(scores[step][2] > 50) return true;
		return false;
	}
	
}
