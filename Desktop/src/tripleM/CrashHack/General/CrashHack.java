package tripleM.CrashHack.General;

import tripleM.CrashHack.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;


public class CrashHack implements ApplicationListener{
	FPSLogger fps;
	private Screen pantalla;
	private Control ctrl;
	
	public CrashHack(Control _ct) {
		ctrl = _ct;
	}
	
	/**
	 * Control register.
	 * ONLY FOR DESKTOP VERSION
	 */
	public void desktop_regControl () {
		Gdx.input.setInputProcessor((ControlDesktop) ctrl);
	}
	
	@Override
	public void create() {
		Setup.load();
		Art.load();
		Sound.load();
		fps = new FPSLogger();
		
		pantalla = new GameScreen(ctrl);
	}

	@Override
	public void dispose() {
		pantalla.dispose();
		ctrl.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,1,1,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		pantalla.render(0);
//		fps.log();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		pantalla.resize(arg0, arg1);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
