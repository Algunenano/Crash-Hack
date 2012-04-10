package tripleM.crashHack;

import java.lang.reflect.Method;
import android.view.MotionEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tripleM.CrashHack.General.Control;
import tripleM.CrashHack.General.UIButton;

/* Notice we don't use a ellipse as touch are because all the devices tested
 * doesn't support it (and return a circle instead)
 */
public class UIButtonAndroid extends UIButton {
	public static Method getTouchMajor = null;
//	public static Method getTouchMinor;
//	public static Method getOrientation;
	public int pressedby;
	
	public UIButtonAndroid(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY, int _id) {
		super(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, _id);
		pressedby = -1;
	}

	
	/**
	 * Remember to change the touch coordinates to texture coordinates (y = Height - y)
	 * @param _x
	 * @param _y
	 * @param _p
	 * @param _event
	 * @return
	 */
	boolean isTouched (int _x, int _y, int _p, MotionEvent _event) {
		if ((!active) || (sprite == null)) return false;
		
		if (getTouchMajor == null)
			return isTouched(_x, _y);

		float rad = 0;
		try {
			rad = (Float) getTouchMajor.invoke(_event, new Object [] {_p});
		} catch (Exception e) {
			Gdx.app.log("API level", "isButtonATouched " + getTouchMajor.getName());
			return isTouched(_x, _y);
		}
		
		// Now we check whether the circle has touched the button box
		
		// X-Coordinate
		float nMin = sprite.getX();
		float nMax = nMin + sprite.getWidth();
		
		if (_x < nMin) {
			if ((_x + rad) < nMin) return false;
		} else {
			if ((_x - rad) > nMax) return false;
		}
		
		//Y-Coordinate
		nMin = sprite.getY();
		nMax = nMin + sprite.getHeight();
		
		if (_y < nMin) {
			if ((_y + rad) < nMin) return false;
		} else {
			if ((_y - rad) > nMax) return false;
		}
		
		pressedby = _p;
		return true;
	}
	
	@Override
	public void unpress () {
		pressedby = -1;
		pressed = false;
	}
}

class UIButtonAndroid_A extends UIButtonAndroid {
	
	public UIButtonAndroid_A(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY, int _id) {
		super(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, _id);
	}

	@Override
	public void press () {
		pressed = true;
		changeSprite(ArtControlAndroid.aButtonPressed);
	}
	
	@Override
	public void unpress () {
		pressed = false;
		changeSprite(ArtControlAndroid.aButtonUnpressed);
	}
	
}

class UIButtonAndroid_B extends UIButtonAndroid {
	
	public UIButtonAndroid_B(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY, int _id) {
		super(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, _id);
	}

	@Override
	public void press () {
		pressed = true;
		changeSprite(ArtControlAndroid.bButtonPressed);
	}
	
	@Override
	public void unpress () {
		pressed = false;
		changeSprite(ArtControlAndroid.bButtonUnpressed);
	}
	
}

class UIButtonAndroid_PAD extends UIButtonAndroid {
	
	private Sprite smallPad;
	
	public UIButtonAndroid_PAD(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY, int _id) {
		super(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, _id);
		smallPad = ArtControlAndroid.smallPad;
	}
	
	@Override
	public void render (SpriteBatch _spritebatch) {
		if (!active || (sprite == null)) return;
		
		int sr = (int) sprite.getHeight() / 4;
		int up = 0;
		int right = 0;
		int x = (int) sprite.getX() + (int) (sprite.getWidth() / 2) - (int) (smallPad.getWidth() / 2);
		int y = (int) sprite.getY() + (int) (sprite.getHeight() / 2) - (int) (smallPad.getHeight() / 2);
		
		if (pressed) {
			up -= ControlAndroid.buttons.get(Control.UP).isPressed() ? sr : 0;
			up += ControlAndroid.buttons.get(Control.DOWN).isPressed() ? sr : 0;
			right += ControlAndroid.buttons.get(Control.RIGHT).isPressed() ? sr : 0;
			right -= ControlAndroid.buttons.get(Control.LEFT).isPressed() ? sr : 0;
			
			if ((up != 0) && (right != 0))
			{
				up /= Math.sqrt(2);
				right /= Math.sqrt(2);
			}
		}
		
		smallPad.setPosition(x + right, y + up);
		
		sprite.draw(_spritebatch);
		smallPad.draw(_spritebatch);
	}
	
	@Override
	public void resize (int _width, int _height) {
		if (!active || (sprite == null)) return;
		
		float newWidth = Math.max(minSizeX, Math.min(maxSizeX, _width * ratio));
		float newHeight = Math.max(minSizeY, Math.min(maxSizeY, _height * ratio));
		
		sprite.setSize(newWidth, newHeight);
		smallPad.setSize (newWidth / 2, newHeight / 2);
		
		float finPosX = (relPosX >= 0 ? relPosX : _width + relPosX);
		float finPosY = (relPosY >= 0 ? relPosY : _height + relPosY);
		
		sprite.setPosition(finPosX, finPosY);
		smallPad.setPosition(finPosX + (newWidth / 2), finPosY + (newHeight / 2));
	}
	
}