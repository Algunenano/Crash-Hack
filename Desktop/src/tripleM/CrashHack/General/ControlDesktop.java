package tripleM.CrashHack.General;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ControlDesktop implements Control, InputProcessor {
	SpriteBatch spriteBatch;
	
	static ArrayList<UIButton> buttons = new ArrayList<UIButton>(TOTALBUTTONS);
	static private int buttonsId;
	
	public ControlDesktop() {
		buttonsId = 0;
		for (int i = 0; i < TOTALBUTTONS; i++)
		{
			buttons.add(new UIButton(null, 0, 0, 0, 0, buttonsId++));
		}
	}
	
	public void loadArt(){
	}
	
	@Override
	public int addUIButton(Sprite _sprite, int _minSizeX, int _minSizeY,
			int _maxSizeX, int _maxSizeY) {
		buttons.add(new UIButton(_sprite, _minSizeX, _minSizeY, _maxSizeX, _maxSizeY, buttonsId));
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

	@Override
	public boolean keyDown(int _key) {
		buttons.get(ANYTHING).press();
		
		int button = -1;
		
		if (_key == Keys.DPAD_UP) button = UP;
		if (_key == Keys.DPAD_LEFT) button = LEFT;
		if (_key == Keys.DPAD_DOWN) button = DOWN;
		if (_key == Keys.DPAD_RIGHT) button = RIGHT;
		if (_key == Keys.C) button = A;
		if (_key == Keys.D) button = B;
		
		if ((button != -1)) {
			Gdx.app.log ("Key pressed","" + button);
			buttons.get(button).press();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int _key) {
		buttons.get(ANYTHING).press();
		
		int button = -1;
		
		if (_key == Keys.DPAD_UP) button = UP;
		if (_key == Keys.DPAD_LEFT) button = LEFT;
		if (_key == Keys.DPAD_DOWN) button = DOWN;
		if (_key == Keys.DPAD_RIGHT) button = RIGHT;
		if (_key == Keys.C) button = A;
		if (_key == Keys.D) button = B;
		
		if ((button != -1)) {
			Gdx.app.log ("Key unpressed","" + button);
			buttons.get(button).unpress();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		buttons.get(ANYTHING).press();
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		buttons.get(ANYTHING).press();
		return false;
	}
	

	@Override
	public boolean touchDown(int _x, int _y, int _p, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int _x, int _y, int _p) {
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean touchUp(int _x, int _y, int _p, int arg3) {
		buttons.get(ANYTHING).press();
		
		boolean ret = false;
		int texY = Gdx.graphics.getHeight() - _y;
		
		for (int i = 0; i < buttons.size(); i++)
		{
			if (buttons.get(i).isTouched(_x, texY)) {
				buttons.get(i).press();
				ret = true;
			}
		}
		
		return ret;
	}

	@Override
	public void render(float delta) {
		spriteBatch.begin();
		
		for (int i = TOTALBUTTONS - 1; i < buttons.size(); i++) {
			buttons.get(i).render(spriteBatch);		
			buttons.get(i).unpress();
		}
		
		spriteBatch.end();
		
		buttons.get(ANYTHING).unpress();
	}

	@Override
	public void resize(int _width, int _height) {
		spriteBatch = new SpriteBatch();
		
		for (int i = TOTALBUTTONS - 1; i < buttons.size(); i++)
		{	
			buttons.get(i).resize(_width, _height);
		}
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
}
