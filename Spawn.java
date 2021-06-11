package danceGame;

import java.util.Random;

public class Spawn {

	private HUD hud;
	private Handler handler;
	private Random random;
	private int limit = 50;

	private float keepScore = 0;

	public Spawn(HUD hud, Handler handler) {
		this.hud = hud;
		this.handler = handler;

		random = new Random();

	}

	public void tick() {

		keepScore += 0.4f;	
		
		int number = random.nextInt(10);

		if (keepScore >= limit) {
			//if (hud.getLevel() % 10 != 0) {
				 
				//handler.removeBoss();
				
				//number == 0 is the start of game 
				if (number == 0)
					//help with this  (150,-40) spawn? 
					handler.addObject(new FallingBlock(150, -90, ID.FallingBlockE, handler));
							
				// if the level is greater than start of game and below lvl 7 then 
				else if (number > 0 && number < 5)
					handler.addObject(new FallingBlock(150,-40, ID.FallingBlockQ, handler));
				// this after lvl 7 
				else
					handler.addObject(new FallingBlock(340,-40, ID.FallingBlockW, handler));
				//	handler.addObject(new FallingBlock(440,-90, ID.FallingBlockE, handler));
				limit = 40;	 
				//for (int i = 0; keepScore < 500; i++) {
					 // System.out.println("test");
					
			//	limit--;
			//	}
			
			hud.setLevel(hud.getLevel() + 1);
			keepScore = 0;

		}

	}

	public void setKeepScore(int keepScore) {
		this.keepScore = keepScore;
	}

}