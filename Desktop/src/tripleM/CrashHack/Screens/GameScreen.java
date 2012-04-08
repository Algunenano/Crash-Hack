package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private SpriteBatch spriteBatch;
	private Control control;

	
	public GameScreen(Control _c) {
		spriteBatch = new SpriteBatch();
		control = _c;
		control.loadArt();
		control.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		control.render(arg0);
	}

	@Override
	public void resize(int _width, int _height) {
		control.resize(_width, _height);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
