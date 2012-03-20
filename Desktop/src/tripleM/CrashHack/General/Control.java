package tripleM.CrashHack.General;

import com.badlogic.gdx.Gdx;

public class Control
{
	private double padX;
	private double padY;
	private double padSize;
	private boolean padEnabled;
	private double buttonAX;
	private double buttonAY;
	private double buttonASize;
	private boolean buttonAEnabled;
	private double buttonBX;
	private double buttonBY;
	private double buttonBSize;
	private boolean buttonBEnabled;
	
	public Control ()
	{
		padEnabled = false;
		buttonAEnabled = false;
		buttonBEnabled = false;
	}
	
	public void placePad(double padX, double padY, double padSize)
	{
		this.padX = padX;
		this.padY = padY;
		this.padSize = padSize * padSize / 4;
	}
	
	public void placeButtonA(double buttonAX, double buttonAY, double buttonASize)
	{
		this.buttonAX = buttonAX;
		this.buttonAY = buttonAY;
		this.buttonASize = buttonASize / 2;
	}
	
	public void placeButtonB(double buttonBX, double buttonBY, double buttonBSize)
	{
		this.buttonBX = buttonBX;
		this.buttonBY = buttonBY;
		this.buttonBSize = buttonBSize / 2;
	}
	
	public boolean isEnabledPad()
	{
		return padEnabled;
	}
	
	public void enablePad()
	{
		padEnabled = true;
	}
	
	public void disablePad()
	{
		padEnabled = false;
	}
	
	public boolean isEnabledButtonA()
	{
		return buttonAEnabled;
	}
	
	public void enableButtonA()
	{
		buttonAEnabled = true;
	}
	
	public void disableButtonA()
	{
		buttonAEnabled = false;
	}
	
	public boolean isEnabledButtonB()
	{
		return buttonBEnabled;
	}
	
	public void enableButtonB()
	{
		buttonBEnabled = true;
	}
	
	public void disableButtonB()
	{
		buttonBEnabled = false;
	}
	
	public int getButtonTouched()
	{
		if (!Gdx.input.isTouched()) return -1;
		if (padEnabled && isPadTouched()) return 0;
		if (buttonAEnabled && isButtonATouched()) return 1;
		if (buttonBEnabled && isButtonBTouched()) return 0;
		return -1;
	}
	
	public boolean isPadTouched()
	{
		double x, y;
		
		if (!(padEnabled && Gdx.input.isTouched())) return false;
		if (Gdx.input.getX() < padX)
			x = padX - Gdx.input.getX();
		else
			x = Gdx.input.getX() - padX;
		if (Gdx.input.getY() < padY)
			y = padY - Gdx.input.getY();
		else
			y = Gdx.input.getY() - padY;
		return x * x + y * y <= padSize;
	}
	
	public boolean isButtonATouched()
	{
		if (!(buttonAEnabled && Gdx.input.isTouched())) return false;
		return Gdx.input.getX() >= buttonAX - buttonASize && Gdx.input.getX() <= buttonAX + buttonASize && Gdx.input.getY() >= buttonAY - buttonASize && Gdx.input.getY() <= buttonAY + buttonASize;
	}
	
	public boolean isButtonBTouched()
	{
		if (!(buttonBEnabled && Gdx.input.isTouched())) return false;
		return Gdx.input.getX() >= buttonBX - buttonBSize && Gdx.input.getX() <= buttonBX + buttonBSize && Gdx.input.getY() >= buttonBY - buttonBSize && Gdx.input.getY() <= buttonBY + buttonBSize;
	}
	
	public double getPadX()
	{
		return Gdx.input.getX() - padX;
	}
	
	public double getPadY()
	{
		return padY - Gdx.input.getY();
	}
	
	public void drawControl()
	{
		//Algún dia esta función dibujara los controles sobre la pantalla
	}
}
