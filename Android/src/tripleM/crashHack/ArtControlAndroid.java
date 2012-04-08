package tripleM.crashHack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ArtControlAndroid {

	public Texture allControls;
	public Sprite bigPad;
	public float sizeBigPad = 64f;
	
	public Sprite smallPad;
	public float sizeSmallPad = 32f;
	
	public Sprite aButton;
	private float[] colorA = {0,1,0,1};
	public float sizeA = 32f;
	private boolean pressedA;
	
	public Sprite bButton;
	private float[] colorB = {1,0,0,1};
	public float sizeB = 32f;
	private boolean pressedB;
	
	public BitmapFont droidFont_32;
	
	public void pressA() {
		if (pressedA) return;
		float h = aButton.getHeight();
		float w = aButton.getWidth();
		float x = aButton.getX();
		float y = aButton.getY();
		aButton 	= new Sprite (allControls, 128, 32, (int) sizeA, (int) sizeA);
		aButton.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		aButton.setSize(w, h);
		aButton.setPosition(x, y);
		pressedA = true;
	}
	
	public void unpressA() {
		if (! pressedA) return;
		float h = aButton.getHeight();
		float w = aButton.getWidth();
		float x = aButton.getX();
		float y = aButton.getY();
		aButton 	= new Sprite (allControls, 128, 0, (int) sizeA, (int) sizeA);
		aButton.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		aButton.setSize(w, h);
		aButton.setPosition(x, y);
		pressedA = false;
	}
	
	public void pressB() {
		if (pressedB) return;
		float h = bButton.getHeight();
		float w = bButton.getWidth();
		float x = bButton.getX();
		float y = bButton.getY();
		bButton 	= new Sprite (allControls, 192, 32, (int) sizeB, (int) sizeB);
		bButton.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		bButton.setSize(w, h);
		bButton.setPosition(x, y);
		pressedB = true;
	}
	
	public void unpressB() {
		if (! pressedB) return;
		float h = bButton.getHeight();
		float w = bButton.getWidth();
		float x = bButton.getX();
		float y = bButton.getY();
		bButton 	= new Sprite (allControls, 192, 0, (int) sizeB, (int) sizeB);
		bButton.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		bButton.setSize(w, h);
		bButton.setPosition(x, y);
		pressedB = false;
	}
	
	public void load () {
		allControls = new Texture (Gdx.files.internal("res/hud.png"));
		bigPad 		= new Sprite (allControls, 0, 0, (int) sizeBigPad, (int) sizeBigPad);
		smallPad 	= new Sprite (allControls, 64, 0, (int) sizeSmallPad, (int) sizeSmallPad);
		bigPad.setColor(0.85f, 0.85f, 0.85f, 0.4f);
		smallPad.setColor(0.9f, 0.9f, 0.9f, 0.4f);
		
		
		aButton 	= new Sprite (allControls, 128, 0, (int) sizeA, (int) sizeA);		
		aButton.setColor(colorA[0],colorA[1],colorA[2],colorA[3]);
		pressedA = false;
		
		bButton 	= new Sprite (allControls, 192, 0, (int) sizeB, (int) sizeB);
		bButton.setColor(colorB[0],colorB[1],colorB[2],colorB[3]);
		pressedB = false;
		
		droidFont_32 = new BitmapFont(Gdx.files.internal("res/fonts/droidSansMono_32.fnt"), false);
	}
	
	
}
