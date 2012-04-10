package tripleM.crashHack;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tripleM.CrashHack.General.Control;
import tripleM.CrashHack.General.Setup;
import android.util.Log;
import android.view.*;
import tripleM.crashHack.ArtControlAndroid;

public class ControlAndroid implements Control, View.OnTouchListener {

	private SpriteBatch spriteBatch; //Initialize in resize()
	
	private final float MAXIMUM_RATIO = 2;
	private final float MINIMUM_RATIO = 0.5f;
	private final float PAD_FRACTION_HEIGHT = 3; //Fraction of the screen for the pad
	private final float PAD_FRACTION_WIDTH = 4;
	private final int 	PAD_MARGIN = 10;
	
	private final float AB_FRACTION_WIDTH = 3;
	private final float AB_FRACTION_HEIGHT = 3;
	private final float AB_MARGIN = 15;
		
	private final int MAX_PRECISION = 5; // If the precision of the device is over this we use the old method. Why? Cause fuck'em. Thats why.
	
	public static final int NOTPRESSED = -1;

	private static boolean APILEVELtested;
	
	private ArtControlAndroid art;
	
	static ArrayList<UIButtonAndroid> buttons;
	static private int buttonsId;
	
    public ControlAndroid() {
		buttons = new ArrayList<UIButtonAndroid>();		
    }
    
    public void loadArt ()
    {
    	art = new ArtControlAndroid();
		art.load();
		
		for (buttonsId = 0; buttonsId <= RIGHT; buttonsId++)
		{
			buttons.add(new UIButtonAndroid (null, 0, 0, 0, 0, buttonsId));
		}
		
		int _height = Gdx.graphics.getHeight();
		int _width = Gdx.graphics.getWidth();
		
		// A-B buttons
		float size = ArtControlAndroid.sizeA;
		float ratio = Math.min(
				((_height / AB_FRACTION_HEIGHT) / (2 * size + AB_MARGIN)),
				((_width / AB_FRACTION_WIDTH) / (2 * size + AB_MARGIN + MAX_PRECISION)));
		if (ratio > MAXIMUM_RATIO) ratio = MAXIMUM_RATIO;
		if (ratio < MINIMUM_RATIO) ratio = MINIMUM_RATIO;		
		int wid = (int) (size * ratio);
		
		ArtControlAndroid.aButtonUnpressed.setSize(wid, wid);
		ArtControlAndroid.aButtonUnpressed.setPosition 
			(_width - (3 * AB_MARGIN + wid), AB_MARGIN);
		
		ArtControlAndroid.bButtonUnpressed.setSize(wid, wid);
		ArtControlAndroid.bButtonUnpressed.setPosition
			(_width - AB_MARGIN, wid);

		buttons.add(new UIButtonAndroid_A(ArtControlAndroid.aButtonUnpressed, 
				(int) (size * MINIMUM_RATIO), (int) (size * MINIMUM_RATIO), 
				(int) (size * MAXIMUM_RATIO), (int) (size * MAXIMUM_RATIO),
				buttonsId++));
		buttons.add(new UIButtonAndroid_B(ArtControlAndroid.bButtonUnpressed, 
				(int) (size * MINIMUM_RATIO), (int) (size * MINIMUM_RATIO), 
				(int) (size * MAXIMUM_RATIO), (int) (size * MAXIMUM_RATIO),
				buttonsId++));
		
		//PAD
		
		size = ArtControlAndroid.sizeBigPad;
		ratio = Math.min(
				(((float) _height / PAD_FRACTION_HEIGHT) / (size + PAD_MARGIN)),
				(((float) _width / PAD_FRACTION_WIDTH) / (size + PAD_MARGIN)));
		if (ratio > MAXIMUM_RATIO) ratio = MAXIMUM_RATIO;
		else if (ratio < MINIMUM_RATIO) ratio = MINIMUM_RATIO;
		wid = (int) (size * ratio);

		ArtControlAndroid.bigPad.setSize(wid, wid);
		ArtControlAndroid.bigPad.setPosition(PAD_MARGIN, PAD_MARGIN);
		
		buttons.add(new UIButtonAndroid_PAD(ArtControlAndroid.bigPad,
				(int) (size * MINIMUM_RATIO), (int) (size * MINIMUM_RATIO),
				(int) (size * MAXIMUM_RATIO), (int) (size * MAXIMUM_RATIO),
				buttonsId++));
		
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    public void setupAPI()
    {
		if (Setup.getConfigInt(Setup.fatfingers, 0) == 1)
		{
			APILEVELtested = true;
		}
		else
		{
			APILEVELtested = true;
			try {
				Class<?> c[] = new Class[1];
				c[0] = int.class;
				
				UIButtonAndroid.getTouchMajor = MotionEvent.class.getMethod("getTouchMajor", c);
//				UIButtonAndroid.getTouchMinor = MotionEvent.class.getMethod("getTouchMinor", c);
//				UIButtonAndroid.getOrientation  = MotionEvent.class.getMethod("getOrientation", c);			
			} catch (Exception e) {
				Log.v("API level", "Unable of detecting touched areas");
			}
		}
    }

	@Override
	public int addUIButton(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY) {
		buttons.add(new UIButtonAndroid(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, buttonsId));
		return buttonsId++;
	}

	@Override
	public boolean isPressed(int _id) {
		for (int i = 0; i < buttons.size(); i++)
			if (buttons.get(i).id == _id) return buttons.get(i).isPressed();
		Gdx.app.log("ControlDesktop.isPressed()", "Wrong id passed");
		return false;
	}

	@Override
	public void press(int _id) {
		for (int i = 0; i < buttons.size(); i++)
			if (buttons.get(i).id == _id) {
				buttons.get(i).press();
				return;
			}
		Gdx.app.log("ControlDesktop.press()", "Wrong id passed");			
	}

	@Override
	public void unpress(int _id) {
		for (int i = 0; i < buttons.size(); i++)
			if (buttons.get(i).id == _id) {
				buttons.get(i).unpress();
				return;
			}
		Gdx.app.log("ControlDesktop.unpress()", "Wrong id passed");			
	}
    
	public boolean touchDown(int _x, int __y, int _p, MotionEvent _event) {
		boolean aux = false;
		buttons.get(ANYTHING).press();
		int texY = Gdx.graphics.getHeight() - __y;
		
		// Buttons A & B
		if ( (!buttons.get(A).isPressed()) && buttons.get(A).isTouched(_x, texY, _p, _event)) {
			buttons.get(A).press();
			aux = true;
		}
			
		if ( !buttons.get(B).isPressed() && buttons.get(B).isTouched(_x, texY, _p, _event)) {
			buttons.get(B).press();
			aux = true;
		}
		
		// PAD
		if ( !buttons.get(PAD).isPressed() && buttons.get(PAD).isTouched(_x, texY, _p, _event)) {
			buttons.get(PAD).press();
			
			float padRad = buttons.get(PAD).sprite.getHeight() / 2;
			float padX = buttons.get(PAD).sprite.getX() + padRad;
			float padY = buttons.get(PAD).sprite.getY() + padRad;
			
			
			if (texY < (padY - 0.3 * padRad))
				buttons.get(UP).press();
			else buttons.get(UP).unpress();
			
			if (texY > (padY + 0.3 * padRad))
				buttons.get(DOWN).press();
			else buttons.get(DOWN).unpress();
			
			if (_x > (padX + 0.3 * padRad))
				buttons.get(RIGHT).press();
			else buttons.get(RIGHT).unpress();
			
			if (_x < (padX - 0.3 * padRad))
				buttons.get(LEFT).press();
			else buttons.get(LEFT).unpress();
			
			aux = true;
		}
		
		//Other
		for (int i = TOTALBUTTONS; i < buttons.size(); i++) {
			UIButtonAndroid ba = buttons.get(i);
			if ((!ba.isPressed()) && (ba.isTouched(_x, texY, _p, _event))) {
				buttons.get(i).press();
				aux = true;
			}
		}
		
		return aux;
	}

	public boolean touchDragged(int _x, int __y, int _p, MotionEvent _event) {
		boolean aux = false;
		int texY = Gdx.graphics.getHeight() - __y;
		
		buttons.get(ANYTHING).press();
		
		for (int i = 0; i < buttons.size(); i++) {
			UIButtonAndroid b = buttons.get(i);
			if (b.isPressed() && (b.pressedby == _p)) {
				if (b.id != PAD) {
					if (! b.isTouched(_x, texY, _p, _event)) b.unpress();
				} else {
					float padRad = buttons.get(PAD).sprite.getHeight() / 2;
					float padX = buttons.get(PAD).sprite.getX() + padRad;
					float padY = buttons.get(PAD).sprite.getY() + padRad;
					
					
					if (texY < (padY - 0.3 * padRad))
						buttons.get(UP).press();
					else buttons.get(UP).unpress();
					
					if (texY > (padY + 0.3 * padRad))
						buttons.get(DOWN).press();
					else buttons.get(DOWN).unpress();
					
					if (_x > (padX + 0.3 * padRad))
						buttons.get(RIGHT).press();
					else buttons.get(RIGHT).unpress();
					
					if (_x < (padX - 0.3 * padRad))
						buttons.get(LEFT).press();
					else buttons.get(LEFT).unpress();
				}
 			} else {
 				if (b.isTouched(_x, texY, _p, _event))
 					b.press();
 			}
		}
		
		return aux;
	}

	public boolean touchUp(int _x, int _y, int _p, MotionEvent _event) {
		boolean ret = false;
		
		buttons.get(ANYTHING).press();

		for (int i = 0; i < buttons.size(); i++) {
			UIButtonAndroid ba = buttons.get(i);
			if ((ba.isPressed()) && (ba.pressedby == _p)) {
				buttons.get(i).unpress();
				ret = true;
			}
		}

		return ret;
	}

	@Override
	public void render(float delta) {
		spriteBatch.begin();
		
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(spriteBatch);
			
		spriteBatch.end();
		
		buttons.get(ANYTHING).unpress();
		
	}

	@Override
	public void resize(int _width, int _height) {

		spriteBatch = new SpriteBatch();
		
		for (int i = 0; i < buttons.size(); i++)
			buttons.get(i).resize(_width, _height);		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
	
	/**
	 * Based on AndroidMultiTouchHandler 
	 * (gdx-backend-android/src/com/badlogic/gdx/backends/android/AndroidMultiTouchHandler.java)
	 * by badlogicgames@gmail.com
	 * Licensed under the Apache License, Version 2.0
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		final int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		int pointerId = event.getPointerId( pointerIndex );

		int x = 0, y = 0;
		
		if ( !APILEVELtested)
		{
			APILEVELtested = true;
			if (event.getXPrecision() > MAX_PRECISION)
			{
				UIButtonAndroid.getTouchMajor = null;
				Gdx.app.log("Precision", "Yours sucks. So we use old method instead");
			}				
		}
		
		switch( action )
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				x = (int)event.getX( pointerIndex );
				y = (int)event.getY( pointerIndex );
				touchDown(x, y, pointerId, event);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_OUTSIDE:
			case MotionEvent.ACTION_CANCEL:
				x = (int)event.getX( pointerIndex );
				y = (int)event.getY( pointerIndex );
				touchUp(x, y, pointerId, event);
				break;

			case MotionEvent.ACTION_MOVE:
				int pointerCount = event.getPointerCount();
				for( int i = 0; i < pointerCount; i++ )
				{
					pointerIndex = i;
					pointerId = event.getPointerId( pointerIndex );
					x = (int)event.getX( pointerIndex );
					y = (int)event.getY( pointerIndex );
					touchDragged(x, y, pointerId, event);
				}
				break;
		}
		
		return true;
	}
	
}
