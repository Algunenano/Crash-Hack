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
		
		bigPad 		= new Sprite (allControls, 1, 0, 64, 64);
		bigPad.setPosition(10, 10);
		
		smallPad 	= new Sprite (allControls, 65, 0, 32, 32);
		smallPad.setPosition(26,26);
		
		aButton 	= new Sprite (allControls, 129, 0, 32, 32);
		aButton.setPosition(CrashHack.GAME_WIDTH - 84, 10);
		aButton.setColor(0f, 1f, 0f, 1f);
		
		bButton 	= new Sprite (allControls, 193, 0, 32, 32);
		bButton.setPosition(CrashHack.GAME_WIDTH - 42, 42);
		bButton.setColor(1f, 0f, 0f, 1f);
		
	}
	
	
}
