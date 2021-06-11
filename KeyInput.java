package danceGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Scorezone2 scorezone;
	private Handler handler;
	private float speed = 4;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) { 
	int key = e.getKeyCode();

			// key events for player
	//         keys it waiting for to delete blocks
			if (key == KeyEvent.VK_W || key == KeyEvent.VK_Q || key == KeyEvent.VK_E ) {
				handler.collision();
								}
										
		//Pause the game
		if (Game.gameState == STATE.Game)
			if (key == KeyEvent.VK_P) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}

		//Exit the game
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		
		
		
			
		

	}

	public void keyReleased(KeyEvent e) {}
		
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
	
}