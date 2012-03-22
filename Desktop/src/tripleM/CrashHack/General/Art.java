package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Art {

	public static Texture allControls;
	public static Sprite bigPad;
	public static Sprite smallPad;
	public static Sprite aButton;
	public static Sprite bButton;
	
	public static void load () {
		allControls = new Texture (Gdx.files.internal("res/hud.png"));
		bigPad 		= new Sprite (allControls, 0, 0, 64, 64);
		smallPad 	= new Sprite (allControls, 64, 0, 32, 32);
		
		
		aButton 	= new Sprite (allControls, 128, 0, 32, 32);		
		aButton.setColor(0f, 1f, 0f, 1f);
		
		bButton 	= new Sprite (allControls, 192, 0, 32, 32);
		bButton.setColor(1f, 0f, 0f, 1f);
		
	}
	
	
}
