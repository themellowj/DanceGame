package danceGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {


	private static final long serialVersionUID = -1560235737482091290L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
//480 height
	// Objects
	private boolean running = false; 
	private Thread thread; 
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	
	public static STATE gameState = STATE.Menu;
	public static boolean paused = false;
	public static boolean hard = true;
	private Random random = new Random();

	public Game() {
		hud = new HUD();
		handler = new Handler();
		menu = new Menu(handler, hud);
		spawn = new Spawn(hud, handler);

		
		this.addKeyListener(new KeyInput(handler)); 
		this.addMouseListener(menu);

	
		
		// the window
		new Window(WIDTH, HEIGHT, "Let's Build a Game", this);

		if (gameState == STATE.Menu) {
			for (int i = 0; i < 20; i++)
				handler.addObject(new MenuParticle(random.nextInt(Game.WIDTH - 20), random.nextInt(Game.HEIGHT - 40),
						ID.MenuParticle, handler));
		}

	}

	public synchronized void start() {
		thread = new Thread(this); 
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join(); 
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Game loop
	public void run() {
		this.requestFocus(); 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {

		if (gameState == STATE.Game) {

			if (!paused) {

				handler.tick(); 
				hud.tick();
				spawn.tick();

				if (HUD.HEALTH <= 0)
					gameState = STATE.GameOver;
				

			}
		} else if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.Help || gameState == STATE.Difficulty) {
			handler.tick();//Only for MenuParticles
			menu.tick();
			HUD.HEALTH = 100;
			spawn.setKeepScore(0);// Because the level up doesn't work in 100 points

		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();// THIS refers to Canvas

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		 
		g.setColor(new Color(30, 30, 30));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(Game.paused) {
			g.setColor(Color.white);
			g.drawString("Paused", 100, 100);		
		}

		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.Difficulty || gameState == STATE.Upgrade)
			menu.render(g);

		g.dispose();
		bs.show();

	}

	public static float clamp(float var, float min, float max) {
		if (var <= min)
			return min;
		else if (var >= max)
			return max;
		else
			return var;
	}

	public static void main(String args[]) {
		new Game();
	}

}