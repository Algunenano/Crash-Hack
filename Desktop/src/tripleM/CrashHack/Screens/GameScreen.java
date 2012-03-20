package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Art;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	SpriteBatch spriteBatch;

	
	public GameScreen() {
		spriteBatch = new SpriteBatch();
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
		
		spriteBatch.begin();
		
//		if (Gdx.app.getType() == ApplicationType.Android) {
			Art.bigPad.draw(spriteBatch);
			Art.smallPad.draw(spriteBatch);
			Art.aButton.draw(spriteBatch);
			Art.bButton.draw(spriteBatch);
//		}
		
		spriteBatch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

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
