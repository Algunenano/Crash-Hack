package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Control implements InputProcessor
{
	private static final int TOTALBUTTONS = 7;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int A = 4;
	public static final int B = 5;
	public static final int PAD = 6;
	public int[] pressed;
	public int[] previous;
	public static final int NOTPRESSED = -1;
	
	
	private int padX;
	private int padY;
	private int padRad;
	private int buttonAX;
	private int buttonAY;
	private int buttonARad;
	private int buttonBX;
	private int buttonBY;
	private int buttonBRad;	
	
	
	
	public Control ()
	{
		pressed = new int [TOTALBUTTONS];
		for (int i = 0; i < TOTALBUTTONS; i++)
			pressed[i] = NOTPRESSED;
			
	}
	
	public void placePad(int _padX, int _padY, int _padRad)
	{
		this.padX = _padX;
		this.padY = _padY;
		this.padRad = _padRad;
	}
	
	public void placeButtonA(int _buttonAX, int _buttonAY, int _buttonARad)
	{
		this.buttonAX = _buttonAX;
		this.buttonAY = _buttonAY;
		this.buttonARad = _buttonARad;		
	}
	
	public void placeButtonB(int _buttonBX, int _buttonBY, int _buttonBRad)
	{
		this.buttonBX = _buttonBX;
		this.buttonBY = _buttonBY;
		this.buttonBRad = _buttonBRad;
	}
	
	
	public boolean isPadTouched(int _x, int _y)
	{		
		if ((Math.abs(_x - padX) >= padRad) ||
			(Math.abs(_y - padY) >= padRad))
			return false;
		return true;
	}
	
	public boolean isButtonATouched(int _x, int _y)
	{
		if ((Math.abs(_x - buttonAX) >= buttonARad) ||
			(Math.abs(_y - buttonAY) >= buttonARad))
			return false;
		return true;
	}
	
	public boolean isButtonBTouched(int _x, int _y)
	{
		if ((Math.abs(_x - buttonBX) >= buttonBRad) ||
			(Math.abs(_y - buttonBY) >= buttonBRad))
			return false;
		return true;
	}
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int _x, int _y, int _p, int arg3) {
		boolean aux = false;
		Gdx.app.log("Control", ("Pressed " + _x + " " + _y));
		
		if ((pressed[A] == NOTPRESSED) && isButtonATouched(_x, _y))
		{
			Gdx.app.log("Control", "Pressed A ");
			pressed[A] = _p;
			aux = true;
		}
		
		if ((pressed[B] == NOTPRESSED) && isButtonBTouched(_x, _y))
		{
			Gdx.app.log("Control", "Pressed B");
			pressed[B] = _p;
			aux = true;
		}
		
		if ((pressed[PAD] == NOTPRESSED) && (isPadTouched(_x, _y)))
		{
			Gdx.app.log("Control", "Pressed PAD");
			pressed[PAD] = _p;
			aux = true;
			pressed[UP] 	= (_y < (padY - 0.3 * padRad)) ? _p : -1;
			pressed[DOWN] 	= (_y > (padY + 0.3 * padRad)) ? _p : -1;
			pressed[RIGHT] 	= (_x > (padX + 0.3 * padRad)) ? _p : -1;
			pressed[LEFT]	= (_x < (padX - 0.3 * padRad)) ? _p : -1;
		}
		
		return aux;
	}

	@Override
	public boolean touchDragged(int _x, int _y, int _p) {
		boolean aux = false;
		
		// Check (and update) the PAD in case of dragging
		// We don't care about other buttons being dragged
		if (_p == pressed[PAD])
		{
			Gdx.app.log("Control", "Dragged PAD");
			aux = true;
			pressed[UP] 	= (_y < (padY - 0.3 * padRad)) ? _p : -1;
			pressed[DOWN] 	= (_y > (padY + 0.3 * padRad)) ? _p : -1;
			pressed[RIGHT] 	= (_x > (padX + 0.3 * padRad)) ? _p : -1;
			pressed[LEFT]	= (_x < (padX - 0.3 * padRad)) ? _p : -1;
		}
		
		return aux;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		// Not used
		return false;
	}

	@Override
	public boolean touchUp(int _x, int _y, int _p, int arg3) {
		boolean ret = false;
		
		//Test pointer to pressed[]
		for (int i = 0; i < TOTALBUTTONS; i++)
		{
			if (pressed[i] == _p)
			{
				Gdx.app.log("Control", "Up "+(i) + " " + (_p));
				pressed[i] = NOTPRESSED;
				ret = true;
			}
		}

		return ret;
	}
}
