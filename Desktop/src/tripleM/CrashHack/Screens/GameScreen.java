package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Art;
import tripleM.CrashHack.General.Control;
import tripleM.CrashHack.General.CrashHack;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	SpriteBatch spriteBatch;
	Control control;

	
	public GameScreen(Control _c) {
		spriteBatch = new SpriteBatch();
		control = _c;
		
//		if (Gdx.app.getType() == ApplicationType.Android) {
		//Set up button controls
			int rad = (int) Art.aButton.getHeight() / 2;
			control.placeButtonA((int) Art.aButton.getX() + rad, 
								 (int) CrashHack.GAME_HEIGHT - (int) Art.aButton.getY() - rad, 
								 rad);
			rad = (int) Art.bButton.getHeight() / 2;
			control.placeButtonB(
					(int) Art.bButton.getX() + rad, 
				 	(int) CrashHack.GAME_HEIGHT - (int) Art.bButton.getY() - rad, 
				 	rad);
			
			rad = (int) Art.bigPad.getHeight() / 2;
			control.placePad(
					(int) Art.bigPad.getX() + rad,
					(int) CrashHack.GAME_HEIGHT - (int) Art.bigPad.getY() - rad,
					rad);
		
//		}
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
			
			int sr = (int) Art.smallPad.getHeight() / 2;
			int x = (int) Art.bigPad.getX() +sr;
			int y = (int) Art.bigPad.getY() +sr;
			
			
			if (control.pressed[Control.PAD] != Control.NOTPRESSED)
			{
				y += (control.pressed[Control.UP] != Control.NOTPRESSED) ? sr : 0;
				y -= (control.pressed[Control.DOWN] != Control.NOTPRESSED) ? sr : 0;
				x += (control.pressed[Control.RIGHT] != Control.NOTPRESSED) ? sr : 0;
				x -= (control.pressed[Control.LEFT] != Control.NOTPRESSED) ? sr : 0;
			}
			Art.smallPad.setPosition(x, y);
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
