package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private SpriteBatch spriteBatch;
	private Control control;
	private int map;
	private BitmapFont font;

	
	public GameScreen(Control _c) {
		spriteBatch = new SpriteBatch();
		control = _c;
		control.loadArt();
		control.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//font = new BitmapFont(Gdx.files.internal("res/fonts/droidSansMono_32.fnt"), false);
		font = new BitmapFont(Gdx.files.internal("res/fonts/droidSans_Border.fnt"), false);
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float _delta) {
		control.render(_delta);
		spriteBatch.begin();
		font.draw(spriteBatch, "Wooolololo", 100, 100);
		spriteBatch.end();
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
