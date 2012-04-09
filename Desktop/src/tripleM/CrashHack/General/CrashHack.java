package tripleM.CrashHack.General;

import tripleM.CrashHack.Screens.TitleScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;


public class CrashHack implements ApplicationListener{
	FPSLogger fps;
	private static Screen pantalla;
	public static Control ctrl;
	private static boolean disposeControl = false;
	
	private float accum = 0;
	
	public CrashHack(Control _ct) {
		ctrl = _ct;
	}
	
	/**
	 * Control register.
	 * ONLY FOR DESKTOP VERSION
	 */
	public static void desktop_regControl () {
		Gdx.input.setInputProcessor((ControlDesktop) ctrl);
		disposeControl = true;
	}
	
	public static void setScreen (Screen s) {
		pantalla.dispose();
		pantalla = s;
	}
	
	@Override
	public void create() {
		Setup.load();
		Sound.load();
		fps = new FPSLogger();
		pantalla = new TitleScreen(ctrl);
	}

	@Override
	public void dispose() {
		pantalla.dispose();
		if (disposeControl) ctrl.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		if (accum < 1 / 60.0f) return;
		accum = 0;
		
		Gdx.gl.glClearColor(0.5f,0.5f,1,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		pantalla.render(0);
		
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
