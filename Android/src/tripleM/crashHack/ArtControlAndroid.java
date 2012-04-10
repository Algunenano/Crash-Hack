package tripleM.crashHack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ArtControlAndroid {

	static public Texture allControls;
	static public Sprite bigPad;
	static public float sizeBigPad = 64f;
	
	static public Sprite smallPad;
	static public float sizeSmallPad = 32f;
	
	static public Sprite aButtonPressed;
	static public Sprite aButtonUnpressed;
	static private float[] colorA = {0,1,0,1};
	static public float sizeA = 32f;
	
	static public Sprite bButtonPressed;
	static public Sprite bButtonUnpressed;
	static private float[] colorB = {1,0,0,1};
	static public float sizeB = 32f;
	
	public void load () {
		allControls = new Texture (Gdx.files.internal("res/hud.png"));
		bigPad 		= new Sprite (allControls, 0, 0, (int) sizeBigPad, (int) sizeBigPad);
		smallPad 	= new Sprite (allControls, 64, 0, (int) sizeSmallPad, (int) sizeSmallPad);
		bigPad.setColor(0.85f, 0.85f, 0.85f, 0.4f);
		smallPad.setColor(0.9f, 0.9f, 0.9f, 0.4f);
		
		
		aButtonPressed 	= new Sprite (allControls, 128, 32, (int) sizeA, (int) sizeA);		
		aButtonPressed.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		aButtonUnpressed = new Sprite (allControls, 128, 0, (int) sizeA, (int) sizeA);
		aButtonUnpressed.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		
		bButtonPressed 	= new Sprite (allControls, 192, 32, (int) sizeB, (int) sizeB);
		bButtonPressed.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		bButtonUnpressed = new Sprite (allControls, 192, 0, (int) sizeB, (int) sizeB);
		bButtonUnpressed.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		
}
	
	
}
