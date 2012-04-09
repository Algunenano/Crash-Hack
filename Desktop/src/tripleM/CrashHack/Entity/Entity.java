package tripleM.CrashHack.Entity;

import Levels.Level;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Entity extends Screen {
	
	public int x = 0, y = 0;
	public static TextureRegion texture = new TextureRegion();
	
	public void render(Level _lvl);
}
