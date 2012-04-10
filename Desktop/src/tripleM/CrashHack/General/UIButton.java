package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIButton {
	protected final float minSizeX, minSizeY;
	protected final float maxSizeX, maxSizeY;
	protected boolean active;
	protected final float ratio;
	protected float relPosX;
	protected float relPosY;
	public final int id;
	
	public Sprite sprite;
	
	protected boolean pressed;
	
	public UIButton(Sprite _sprite,	int _minSizeX, int _minSizeY, int _maxSizeX, int _maxSizeY, int _id) {
		sprite = _sprite;
		
		if (sprite != null) {
			ratio = Math.min(_sprite.getHeight() / Gdx.graphics.getHeight(), 
					_sprite.getWidth() / Gdx.graphics.getWidth());
			relPosY = (_sprite.getY() < (Gdx.graphics.getHeight() - _sprite.getY() + _sprite.getHeight()))
					? _sprite.getY() : -(Gdx.graphics.getHeight() - _sprite.getY() + _sprite.getHeight());
			relPosX = (_sprite.getX() < (Gdx.graphics.getWidth() - _sprite.getX() + _sprite.getWidth()))
					? (_sprite.getX()) : -(Gdx.graphics.getWidth() - _sprite.getX() + _sprite.getWidth());
		} else {
			ratio = 1;
		}
		
		minSizeX = _minSizeX;
		minSizeY = _minSizeY;
		maxSizeX = _maxSizeX;
		maxSizeY = _maxSizeY;
		
		id = _id;
		
		active = true;
		pressed = false;
		
	}
	
	public void changeSprite(Sprite _s) {
		float h = sprite.getHeight();
		float w = sprite.getWidth();
		float x = sprite.getX();
		float y = sprite.getY();
		sprite = _s;
		sprite.setSize(w, h);
		sprite.setPosition(x, y);
	}	
	
	public boolean isPressed () {
		return active && pressed;
	}
	
	public void press () {
		pressed = true;
	}
	
	public void unpress () {
		pressed = false;
	}

	public boolean isActive () {
		return active;
	}
	
	public void activate () {
		active = true;
		pressed = false;
		resize (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void deactivate () {
		active = false;
	}
	
	public void move (float _x, float _y) {
		if (sprite == null) return;
		sprite.setPosition(_x, _y);
	}
	
	
	public void render (SpriteBatch _spritebatch) {
		if (!active || (sprite == null)) return;
		
		sprite.draw(_spritebatch);
	}
	
	public void resize (int _width, int _height) {
		if (!active || (sprite == null)) return;
		
		float newWidth = Math.max(minSizeX, Math.min(maxSizeX, _width * ratio));
		float newHeight = Math.max(minSizeY, Math.min(maxSizeY, _height * ratio));
		
		sprite.setSize(newWidth, newHeight);
		
		float finPosX = (relPosX >= 0 ? relPosX : _width + relPosX);
		float finPosY = (relPosY >= 0 ? relPosY : _height + relPosY);
		
		sprite.setPosition(finPosX, finPosY);
	}
	
	/**
	 * Remember to change the touch coordinates to texture coordinates (y = Height - y)
	 * @param _x
	 * @param _y
	 * @return
	 */
	public boolean isTouched (int _x, int _y) {
		if ((!active) || (sprite == null)) return false;
		return 
				((_x >= sprite.getX()) &&
				(_x <= (sprite.getX() + sprite.getWidth())) &&
				(_y >= sprite.getY()) &&
				(_y <= (sprite.getY() + sprite.getHeight()))
				);
	}
}