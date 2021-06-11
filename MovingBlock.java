package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MovingBlock extends GameObject {

	private Random ran;
	private GameObject player;
	private Handler handler;

	
	private int width = 50, height = 50;
	private Color color = Color.orange;

	public MovingBlock(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		ran = new Random();
		
		
		for (GameObject tempObj : handler.getObject()) {
			if (tempObj.getId() == ID.Player) {
				player = tempObj;
				break;
			}
		}

	}

	public void tick() {
		
		x += velX;
		y += velY;	


		float diffX = x - player.getX() - (width/2);
		float diffY = y - player.getY() - (height/2);

		float distance = (float)Math.sqrt((diffX*diffX) + (diffY*diffY));
		
		velX = (float)(-(ran.nextInt(1)+1)/distance) * diffX; 
		velY = (float)(-(ran.nextInt(1)+1)/distance) * diffY; 
		
		
		//smart block maybe
		//handler.addObject(new Trail((int)x, (int)y, ID.SmartEnemy, handler, width, height, color, 0.1f));
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		
		
		if(Game.gameState == STATE.GameOver)
			handler.removeObject(this);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}