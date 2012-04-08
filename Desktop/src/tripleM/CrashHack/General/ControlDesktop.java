package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class ControlDesktop implements Control, InputProcessor {
	public ControlDesktop() {
		for (int i = 0; i < TOTALBUTTONS; i++)
		{
			Control.actions[i] = false;
		}
		
	}
	
	public void loadArt(){
	}

	public void placePad(int _padX, int _padY, int _padRad) {
	}

	public void placeButtonA(int _buttonAX, int _buttonAY, int _buttonARad) {
	}

	public void placeButtonB(int _buttonBX, int _buttonBY, int _buttonBRad) {
	}

	@Override
	public boolean keyDown(int _key) {
		Control.actions[ANYTHING] = true;
		
		int button = -1;
		
		if (_key == Keys.DPAD_UP) button = UP;
		if (_key == Keys.DPAD_LEFT) button = LEFT;
		if (_key == Keys.DPAD_DOWN) button = DOWN;
		if (_key == Keys.DPAD_RIGHT) button = RIGHT;
		if (_key == Keys.C) button = A;
		if (_key == Keys.D) button = B;
		
		if ((button != -1)) {
			Gdx.app.log ("Key pressed","" + button);
			Control.actions[button] = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int _key) {
		Control.actions[ANYTHING] = true;
		
		int button = -1;
		
		if (_key == Keys.DPAD_UP) button = UP;
		if (_key == Keys.DPAD_LEFT) button = LEFT;
		if (_key == Keys.DPAD_DOWN) button = DOWN;
		if (_key == Keys.DPAD_RIGHT) button = RIGHT;
		if (_key == Keys.C) button = A;
		if (_key == Keys.D) button = B;
		
		if ((button != -1)) {
			Gdx.app.log ("Key unpressed","" + button);
			Control.actions[button] = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		Control.actions[ANYTHING] = true;
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		Control.actions[ANYTHING] = true;
		return false;
	}
	

	@Override
	public boolean touchDown(int _x, int _y, int _p, int arg3) {
		Control.actions[ANYTHING] = true;
		return false;
	}

	@Override
	public boolean touchDragged(int _x, int _y, int _p) {
		Control.actions[ANYTHING] = true;
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean touchUp(int _x, int _y, int _p, int arg3) {
		Control.actions[ANYTHING] = true;
		return false;
	}

	@Override
	public void render(float delta) {
		Control.actions[ANYTHING] = false;
	}

	@Override
	public void resize(int _width, int _height) {		
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
	}
	
	
}
