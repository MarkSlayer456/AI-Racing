package main;

import java.awt.Dimension;

import main.managers.FrameRateManager;
import main.managers.UIManager;

public class Game implements Runnable {

	public static final double GRAPHICS_FRAME_RATE = 16.66667; // 60 fps
	public static final double LOGIC_FRAME_RATE = 16.66667; // 60 fps
	
	public static Game graphics = new Game(new UIManager(new Dimension(400, 400)), new FrameRateManager(GRAPHICS_FRAME_RATE));
	public static Game logic = new Game(null, new FrameRateManager(LOGIC_FRAME_RATE));
	public static Thread graphicsThread;
	public static Thread logicThread;
	
	private UIManager uim;
	private FrameRateManager frm;
	
	public Game(UIManager uim, FrameRateManager frm) {
		this.uim = uim;
		this.frm = frm;
	}
	
	public static void main(String[] args) {
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
