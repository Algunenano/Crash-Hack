package tripleM.CrashHack.General;

import com.badlogic.gdx.Screen;

public interface Control extends Screen {

	public static final int 	TOTALBUTTONS = 8;
	public static final int 	UP = 0;
	public static final int 	DOWN = 1;
	public static final int 	LEFT = 2;
	public static final int 	RIGHT = 3;
	public static final int 	A = 4;
	public static final int 	B = 5;
	public static final int 	PAD = 6;
	public static final int 	ANYTHING = 7;
	
	public static boolean[] actions = new boolean [TOTALBUTTONS];
	
	public void placePad(int _padX, int _padY, int _padRad);
	public void placeButtonA(int _buttonAX, int _buttonAY, int _buttonARad);
	public void placeButtonB(int _buttonBX, int _buttonBY, int _buttonBRad);
	
	
}
