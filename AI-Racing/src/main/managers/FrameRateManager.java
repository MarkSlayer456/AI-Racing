package main.managers;

public class FrameRateManager {
	
	private double oneFrameInterval = 16.66667;
	private double startingTime, endingTime;
	private boolean firstTime = false;
	
	public FrameRateManager(double oneFrameInterval) {
		this.oneFrameInterval = oneFrameInterval;
		this.startingTime = 0;
		this.endingTime = 0;
	}
	
	/**
	 * Only runs the first time the FrameRateManager used.
	 * Setups the starting and ending time variables
	 */
	private void firstTime() {
		this.startingTime = System.currentTimeMillis();
		this.endingTime = System.currentTimeMillis();
		firstTime = true;
	}
	
	/**
	 * Checks to see if the frame executed in a given time frame if it was to fast Thread.sleep is called
	 * if not the program continues.
	 */
	private void checkFrameSpeed() {
		if(this.endingTime - this.startingTime >= this.oneFrameInterval) {
			return;
		} else {
			try {
				Thread.sleep((long) (this.oneFrameInterval - (this.endingTime - this.startingTime)));
				return;
			} catch (InterruptedException e) {
				System.out.println("Failed to check frame speed in FrameRateManager.java, this should not have happened");
			}
		}
		System.out.println("ERROR frame failed to load");
	}
	
	///// Setters /////
	public void setStartingTime(double amount) {
		if(this.firstTime == false)
			firstTime();
		if(amount > 0) {
			this.startingTime = amount;
		}
	}
	
	public void setEndingTime(double amount) {
		if(this.firstTime == false)
			firstTime();
		if(amount > 0) {
			this.endingTime = amount;
		}
		this.checkFrameSpeed();
	}
}