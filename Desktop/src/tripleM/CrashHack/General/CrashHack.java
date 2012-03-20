package tripleM.CrashHack.General;

import tripleM.CrashHack.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


public class CrashHack implements ApplicationListener{
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 320;
	
	private Screen pantalla;
	
	
	@Override
	public void create() {
		Art.load();
		Sound.load();
		pantalla = new GameScreen();
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		pantalla.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		pantalla.render(0);

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	

}
