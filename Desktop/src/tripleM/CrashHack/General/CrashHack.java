package tripleM.CrashHack.General;

import tripleM.CrashHack.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import tripleM.CrashHack.General.Control;


public class CrashHack implements ApplicationListener{
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 320;
	
	private Screen pantalla;
	private Control Ctls;
	
	
	@Override
	public void create() {
		Art.load();
		Sound.load();
		pantalla = new GameScreen();
		
		Ctls = new Control();
		//Pad temporal (esquina inferior izquierda, no se redefine al cambiar de tamaño la pantalla)
		Ctls.placePad(0.15 * Gdx.graphics.getWidth(), 0.80 * Gdx.graphics.getHeight(), 120);
		Ctls.enablePad();
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
		
		//Ejemplo de manejo del pad. Positivo = Arriba o Derecha, Negativo = Abajo o Izquierda.
		if (Ctls.isPadTouched()) //o (getButtonTouched() == 0)
			System.out.println(Ctls.getPadX() + ":" + Ctls.getPadY());
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
