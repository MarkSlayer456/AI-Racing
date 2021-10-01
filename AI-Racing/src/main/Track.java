package main;

import main.objects.CheckPoint;
import main.objects.FinishLine;
import main.objects.Wall;

public class Track {

	private Wall[] walls;
	private CheckPoint[] checkPoints;
	private FinishLine line;
	
	public Track(Wall[] walls, CheckPoint[] checkpoints, FinishLine line) {
		this.walls = walls;
		this.checkPoints = checkpoints;
		this.line = line;	
	}
	
	public Wall[] getWalls() {
		return this.walls;
	}
	
	public CheckPoint[] getCheckPoints() {
		return this.checkPoints;
	}

	public FinishLine getFinishLine() {
		return line;
	}

}
