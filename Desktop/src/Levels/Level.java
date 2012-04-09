package Levels;

import java.util.ArrayList;
import tripleM.CrashHack.Entity.Entity;
import tripleM.CrashHack.General.Control;

public class Level {
	private ArrayList<Entity> entities;
	
	private int level;
	private int[][] floor;
	private int width, height;
	private int playerx, playery;
	private Control _c;
	
	public final static int ST_DEFAULT = 0;
	public final static int ST_NORMAL = 1;
	public final static int ST_ALERT = 2;
	public final static int ST_DEATH = 3;
	public int state;
	
	public Level(int _number) {
		this.respawn(_number);
	}
	
	public void addEntity (Entity _e) {
		entities.add(_e);
	}
	
	public void respawn (int _number) {
		level = _number;
		entities = new ArrayList<Entity>();
		
		state = ST_DEFAULT;		
		//TODO: Load map image and translate it to entities + floor + playerx = spawnx, playery = spawny
	}
	
	public void render() {
		//TODO: Render visible floor
		
		//TODO: Render visible entities
		
		//TODO: Render player
	}
}
