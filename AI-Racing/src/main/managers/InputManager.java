package main.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class InputManager
implements KeyListener, MouseListener, MouseMotionListener
{
	public int mousex;
	public int mousey;
	private ArrayList<Integer> keysDown = new ArrayList<Integer>();

	public void mouseDragged(MouseEvent e) {
		mousex = (int) (e.getX() / UIManager.WIDTH_SCALE);
		mousey = (int) (e.getY() / UIManager.HEIGHT_SCALE);
	}

	public void mouseMoved(MouseEvent e) {
		mousex = (int) (e.getX() / UIManager.WIDTH_SCALE);
		mousey = (int) (e.getY() / UIManager.HEIGHT_SCALE);
	}

	public void mouseClicked(MouseEvent e) {
		mousex = (int) (e.getX() / UIManager.WIDTH_SCALE);
		mousey = (int) (e.getY() / UIManager.HEIGHT_SCALE);
	}

	public void mousePressed(MouseEvent e) {
		mousex = (int) (e.getX() / UIManager.WIDTH_SCALE);
		mousey = (int) (e.getY() / UIManager.HEIGHT_SCALE);
	}

	public void mouseReleased(MouseEvent e) {
		mousex = (int) (e.getX() / UIManager.WIDTH_SCALE);
		mousey = (int) (e.getY() / UIManager.HEIGHT_SCALE);
		//TODO main menu stuff
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (!keysDown.contains(Integer.valueOf(e.getKeyCode()))) {
			keysDown.add(new Integer(e.getKeyCode()));
		}
	}

	public void keyReleased(KeyEvent e) {
		keysDown.remove(new Integer(e.getKeyCode()));
	}

	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == 27) { // esc key, useful for pause
			// TODO ...
		}
	}
	
	public ArrayList<Integer> getKeysDown() {
		return this.keysDown;
	}
}
