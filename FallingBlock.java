package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;

public class FallingBlock extends GameObject {

	private Handler handler;
	//private HUD hud;
	
	// Design  
	private int width = 50;
	private int height = 50;
	// have falling block W be a different color 
	private Color color = Color.red;

	public FallingBlock(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		

		// Speed of basic enemy	
		velY = 3; 
		velX = 0;  
	}

	 
	public void tick() {
		x += velX;
		y += velY;
		

		
		if(Game.hard) {
			if(velX > 0)
				velX += 0.15f;
			else
				velX -= 0.15f;
			
			if(velY > 0)
				velY += 0.15f;
			else
				velY -= 0.15f;
		}
				
		//handler.addObject(new Trail((int) x, (int) y, ID.Trail, handler, width, height, color, 0.1f));

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
		
		
	} 
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.white);
		Font Blocks = new Font("Arial", 1, 40); 
		g.setFont(Blocks);
		if (this.id ==ID.FallingBlockQ) { //checking to see if id is the ID of falling block Q
		g.drawString("Q", (int) x, (int) y + 30);
			
		}
		if (this.id == ID.FallingBlockW) {
			g.drawString("W", (int) x, (int) y + 30);	
		}
		if (this.id == ID.FallingBlockE) {
			//float x - 30;
			//float y - 30;
			velY = 1; 
			velX = 0;
			g.drawString("E", (int) x, (int) y + 30);	
		}
	}



}