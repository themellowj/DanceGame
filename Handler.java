package danceGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {
	Scorezone2 scorezone;
	LinkedList <GameObject> object = new LinkedList <GameObject>();
	private float speed = 4;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempobj = object.get(i);

			tempobj.tick();
		}

	}
	//public void addObject(Scorezone scorezone) {
	public void addScorezone2( Scorezone2 scorezone) {
		this.scorezone = scorezone;
		addObject(scorezone);
		}
	
	public void render(Graphics g) {
		try {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);

				tempObject.render(g);
			}
		} catch (Exception e) {

			System.out.println("Error: " + e.toString());

		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemies() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.Player) {
				object.clear();
				addObject(new Player((int) tempObj.getX(), tempObj.getY(), ID.Player, this));
				addScorezone2(new Scorezone2(-20, 290, ID.Scorezone2, this));
				break;
			}

		}

	}

	public void removeBoss() {
		for (GameObject tempObj : object) {
			if (tempObj.getId() == ID.BossEnemy) {
				removeObject(tempObj);
				break;
			}

		}

	}

	public List<GameObject> getObject() {
		return object;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	public void collision() {
		
		
 		for (int i=0;i<this.object.size();i++ ) {
		GameObject tempObj = this.object.get(i);	
		//checks temp object to ID of falling block 
		if(tempObj.getId() == ID.FallingBlockQ || tempObj.getId() == ID.FallingBlockW || tempObj.getId() == ID.FallingBlockE) {
			//if temp object intersects then handler removes object
			 if (scorezone.getBounds().intersects(tempObj.getBounds())) {
				 this.removeObject(tempObj);
			  HUD.score += 100;
			  //I suggest you put an else clause that prints the bounds of the falling block.
			  //And prints the bounds of the scorezone.
			 } else{ 
				 System.out.println(scorezone.getBounds() + ":::" + tempObj.getBounds());
				  }
			}
		}
	}

	
}