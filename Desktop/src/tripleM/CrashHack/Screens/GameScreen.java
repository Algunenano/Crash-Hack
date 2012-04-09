package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Control;
import tripleM.CrashHack.General.Strings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {
	private SpriteBatch spriteBatch;
	private Control control;
	private BitmapFont font;
	private Texture fTexture;
	
	private int map;

	public GameScreen(Control _c) {
		spriteBatch = new SpriteBatch();
		control = _c;
		control.loadArt();
		control.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fTexture = new Texture(Gdx.files.internal("res/fonts/droidSans_Border.png"));
		fTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		font = new BitmapFont(
				Gdx.files.internal("res/fonts/droidSans_Border.fnt"),
				new TextureRegion(fTexture),
				false);
		font.setScale(0.5f);
		font.setColor(0, 0, 0, 1);
		
		map = 1;
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
		fTexture.dispose();
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
		font.draw(spriteBatch, Strings.wolo, 100, 100);
		font.draw(spriteBatch, 
				"fps:"+Gdx.graphics.getFramesPerSecond(),
				Gdx.graphics.getWidth() - 80,
				Gdx.graphics.getHeight() - 20);
		spriteBatch.end();
		
		//TODO: Render level -- Check level state
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
