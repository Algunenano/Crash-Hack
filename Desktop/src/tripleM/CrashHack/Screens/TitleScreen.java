package tripleM.CrashHack.Screens;

import tripleM.CrashHack.General.Control;
import tripleM.CrashHack.General.CrashHack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen implements Screen {
	
	private SpriteBatch spriteBatch;
	private int time;
	private final int FPS = 60; //Expected frames per second
	private static Texture titleTexture;
	private int WIDHT = 800;
	private int HEIGHT = 640;
	private int x,y,w,h;
	private Control control;
	
	public TitleScreen(Control _c) {
		spriteBatch = new SpriteBatch();
		titleTexture = new Texture (Gdx.files.internal("res/loadscreen_complete.png"));
		time = 0;
		x = WIDHT / 2;
		y = HEIGHT * 3 / 4;
		w = WIDHT / 2;
		h = HEIGHT / 4;
		control = _c;
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
		titleTexture.dispose();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float arg0) {
		time++;
		
		if (time <= (FPS / 2))
			x -= WIDHT / FPS;
		else if (time <= FPS)
			y -= HEIGHT / (2 * FPS);
		else if (time <= 2 * FPS)
		{
			y -= HEIGHT / 2 / FPS;
			x += WIDHT / 2 / FPS;
		} 
		else if (time <= 3 * FPS)
		{
			x -= WIDHT / 8 / FPS;
			w += WIDHT / 8 / FPS;
			h += HEIGHT / 4 / FPS;
		}
		
		Sprite s = new Sprite(titleTexture, x, y, w , h);
		s.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		
		s.draw(spriteBatch);
		
		spriteBatch.end();
		
		/*
		 * TODO: Dibujar menu (Jugar, Opciones, Salir) + Actuar según dónde pinche
		 * ==> SetScreen MenuScreen
		 */		
		if (control.isPressed(Control.ANYTHING))
		{
			
			control.unpress(Control.ANYTHING);
			CrashHack.setScreen(new GameScreen(control));
		}

	}

	@Override
	public void resize(int arg0, int arg1) {
		spriteBatch = new SpriteBatch();
		if (time <= 3 * FPS)
		{
			time = 0;
			x = WIDHT / 2;
			y = HEIGHT * 3 / 4;
			w = WIDHT / 2;
			h = HEIGHT / 4;
		}
	}

	@Override
	public void resume() {
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void show() {
	}

}
