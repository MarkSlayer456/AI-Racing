package main;

import java.awt.Dimension;

import main.ai.Brain;
import main.ai.Population;
import main.managers.FrameRateManager;
import main.managers.UIManager;
import main.objects.Car;
import main.objects.CheckPoint;
import main.objects.FinishLine;
import main.objects.Wall;
import main.utils.TrackReader;

public class Game implements Runnable {

	public static final double GRAPHICS_FRAME_RATE = 16.66667; // 60 fps
	public static final double LOGIC_FRAME_RATE = 16.66667; // 60 fps
	public static final int POP_SIZE = 5000;
	
	
	public static Game graphics = new Game(new UIManager(new Dimension(800, 600)), new FrameRateManager(GRAPHICS_FRAME_RATE));
	public static Game logic = new Game(null, new FrameRateManager(LOGIC_FRAME_RATE));
	public static Thread graphicsThread;
	public static Thread logicThread;
	
	public static Population pop;
	public static Track currentTrack;
	public static TrackReader reader;
	
	private UIManager uim;
	private FrameRateManager frm;
	
	public Game(UIManager uim, FrameRateManager frm) {
		this.uim = uim;
		this.frm = frm;
	}
	
	public static void main(String[] args) {
		//TODO need a map system, map editor? csv file? special kind of file?
		/* special kind of file could be just x's and # 
		 * where # are walls and x's are blank
		 * 
		 */
		Brain.init(Brain.BRAIN_SIZE);
		reader = new TrackReader(500);
		reader.readTrack("src/track");
		currentTrack = new Track(reader.getWalls(), reader.getCheckPoints(), reader.getFinishLine());
		pop = new Population(POP_SIZE, reader.getCheckPoints());
		graphicsThread = new Thread(graphics, "graphics");
		graphicsThread.start();
		logicThread = new Thread(logic, "logic");
		logicThread.start();
	}
	
	@Override
	public void run() {
		UIManager ui = this.getUIManager();
		FrameRateManager fr = this.getFrameRateManager();
		while(true) {
			if(ui == null) {
				fr.setStartingTime(System.currentTimeMillis());
				doLogic();
				fr.setEndingTime(System.currentTimeMillis());
			} else {
				fr.setStartingTime(System.currentTimeMillis());
				ui.draw();
				fr.setEndingTime(System.currentTimeMillis());
			}
		}
	}
	
	public void doLogic() {
		// TODO ...
		
		
		Car[] cars = Game.pop.getCars();
		for(int i = 0; i < cars.length; i++) {
			for(int j = 0; j < currentTrack.getWalls().length; j++) {
				Wall wall = currentTrack.getWalls()[j];
				if(wall == null) break;
				wall.checkIfHit(cars[i]);
			}
			for(int j = 0; j < currentTrack.getCheckPoints().length; j++) {
				CheckPoint point = currentTrack.getCheckPoints()[j];
				if(point == null) break;
				point.checkIfHit(cars[i]);
			}
			FinishLine line = currentTrack.getFinishLine();
			if(line != null) {
				line.checkIfHit(cars[i]);
			}
			
			cars[i].move();
		}
	}
	
	public void terminate() {
		System.exit(0);
	}
	
	public UIManager getUIManager() {
		return this.uim;
	}
	
	public FrameRateManager getFrameRateManager() {
		return this.frm;
	}
	
}
