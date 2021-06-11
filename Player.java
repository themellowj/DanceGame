package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	
	//Design of player
	private int width = 700;
	private int height = 40;
	private Color color = Color.green;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	// Movement of player 
	public void tick() {
	 

		//x = Game.clamp(x, 0, Game.WIDTH - 38);
		//y = Game.clamp(y, 0, Game.HEIGHT - 60);

		collision();// lowers health

	}

 
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	private void collision() {
		//loops in the handler for object
		for (int i=0;i<handler.object.size();i++ ) {
			GameObject tempObj = handler.object.get(i);
				//checks if the object is 
				if (tempObj.getId() == ID.FallingBlockQ  || tempObj.getId() ==  ID.FallingBlockW || tempObj.getId() ==  ID.FallingBlockE) {    
					if (getBounds().intersects(tempObj.getBounds())) {
						//checks to see if falling block if so then its deleted
						HUD.HEALTH--;  //then it removes health 
						HUD.HEALTH--;
						HUD.HEALTH--;
						HUD.HEALTH--;
						handler.removeObject(tempObj);
						
						}
						
					
					}
				}
			}
		
	

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}