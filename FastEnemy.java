package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {

	private Handler handler;
	private HUD hud;


	// Design of object
	private int width = 20;
	private int height = 20;
	private Color color = Color.cyan;

	public FastEnemy(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

		// Speed of basic enemy
		velY = 6 + hud.getLevel()/10;
		velX = 5 + hud.getLevel()/10;
	}

	 
	public void tick() {
		x += velX;
		y += velY;

		 
		if (Game.gameState != STATE.GameOver) {
			if (y <= 0 || y >= Game.HEIGHT - 40)
				velY *= -1;
			if (x <= 0 || x >= Game.WIDTH - 20)
				velX *= -1;
		}
		
		if (y < -100 || y >= Game.HEIGHT + 100 || x < -100 || x >= Game.WIDTH + 100)
			handler.removeObject(this);
		//faster velocity for hard
		if(Game.hard) {
			if(velX > 0)
				velX += 0.00075f;
			else
				velX -= 0.00075f;
			
			if(velY > 0)
				velY += 0.00075f;
			else
				velY -= 0.00075f;
		}
		
		handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, width, height, color, 0.1f));

	}

		
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}