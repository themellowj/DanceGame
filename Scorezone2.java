package danceGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Scorezone2 extends GameObject {
//should delete falling blocks 	
	
	
	private Handler handler;
	//size of the rectangle that deletes the blocks 
	public Scorezone2(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		//x = ?
		//(-20,290)
		width  = 700; 
		height = 50;
		this.handler = handler;
	}
	
	public void tick() {
		}
	public void render(Graphics g) {
		//g.setColor(color);
		//
		g.setColor(Color.red);
		g.drawRect((int)x, (int)y, width, height);
	}

	
	

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	
}
