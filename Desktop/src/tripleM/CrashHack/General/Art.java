package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Art {

	public static Texture allControls;
	public static Sprite bigPad;
	public static float sizeBigPad = 64f;
	
	public static Sprite smallPad;
	public static float sizeSmallPad = 32f;
	
	public static Sprite aButton;
	public static float[] colorA = {0,1,0,1};
	public static float sizeA = 32f;
	
	public static Sprite bButton;
	public static float[] colorB = {1,0,0,1};
	public static float sizeB = 32f;
	
	
	public static void load () {
		allControls = new Texture (Gdx.files.internal("res/hud.png"));
		bigPad 		= new Sprite (allControls, 0, 0, (int) sizeBigPad, (int) sizeBigPad);
		smallPad 	= new Sprite (allControls, 64, 0, (int) sizeSmallPad, (int) sizeSmallPad);
		
		
		aButton 	= new Sprite (allControls, 128, 0, (int) sizeA, (int) sizeA);		
		aButton.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		
		bButton 	= new Sprite (allControls, 192, 0, (int) sizeB, (int) sizeB);
		bButton.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		
	}
	
	
}
