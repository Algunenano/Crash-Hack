package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Art;
import tripleM.CrashHack.General.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private SpriteBatch spriteBatch;
	private Control control;

	
	public GameScreen(Control _c) {
		int rad;
		spriteBatch = new SpriteBatch();
		control = _c;
		
//		if (Gdx.app.getType() == ApplicationType.Android) {
		//Set up button controls
		
			// PAD
			
			float ratio = (Gdx.graphics.getHeight() / 3) / Art.bigPad.getHeight();
			rad = (int) Art.bigPad.getHeight() / 2;
			
			Art.bigPad.setPosition(10, 10);
			Art.smallPad.setPosition(Art.bigPad.getX() + rad, Art.bigPad.getY() + rad);
			
			Art.bigPad.setSize(Art.bigPad.getWidth()*ratio, Art.bigPad.getHeight()*ratio);			
			Art.smallPad.setSize(Art.smallPad.getWidth()*ratio, Art.smallPad.getHeight()*ratio);
			
			rad *= ratio;
			control.placePad(
					(int) (Art.bigPad.getX() + rad),
					(int) (Gdx.graphics.getHeight() - Art.bigPad.getY() - rad),
					(int) (rad * 1.1)); //Added 10% error margin
		
			
			// A and B buttons
			ratio = (Gdx.graphics.getHeight() / 6) / Art.aButton.getHeight();
			rad = (int) Art.aButton.getHeight() / 2;
			int wid = (int) (Art.aButton.getWidth() * ratio);
			
			
			Art.bButton.setPosition(Gdx.graphics.getWidth() - 10 - wid, 10 + wid);
			Art.aButton.setPosition(Gdx.graphics.getWidth() - 20 - 2 * wid, 10);
			
			Art.bButton.setSize(Art.bButton.getWidth()*ratio, Art.bButton.getHeight()*ratio);
			Art.aButton.setSize(Art.aButton.getWidth()*ratio, Art.aButton.getHeight()*ratio);
			
			rad *= ratio;
			
			control.placeButtonA(
					(int) Art.aButton.getX() + rad, 
					(int) Gdx.graphics.getHeight() - (int) Art.aButton.getY() - rad, 
					(int) (rad * 1.1));
			
			

			control.placeButtonB(
					(int) Art.bButton.getX() + rad,
				 	(int) Gdx.graphics.getHeight() - (int) Art.bButton.getY() - rad, 
				 	(int) (rad * 1.1));
			
			

		
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
			
			int sr = (int) Art.bigPad.getHeight() / 4;
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
