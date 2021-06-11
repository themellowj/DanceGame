package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBullets extends GameObject {

	private Handler EnemyBullets;
	private Random random;

	// Design  
	private int width = 10;
	private int height = 10;
	private Color color = Color.white;

	public EnemyBullets(float x, float y, ID id, Handler handler, int velY) {
		super(x, y, id);
		this.EnemyBullets = handler;

		random = new Random();
		// Speed of basic enemy
		velX = random.nextInt(10) - 5;
		this.velY = velY;
	}

	 
	public void tick() {
		x += velX;
		y += velY;

		// To hit the screen and don't go any further
		if (Game.gameState != STATE.GameOver) {
			if (x <= 0 || x >= Game.WIDTH - 20)
				velX *= -1;
		}

		if (y > Game.HEIGHT)
			EnemyBullets.removeObject(this);

		EnemyBullets.addObject(new Trail((int) x, (int) y, ID.Trail, EnemyBullets, width, height, color, 0.15f));

	}

	 
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
