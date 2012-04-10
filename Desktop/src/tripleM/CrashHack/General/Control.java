package tripleM.CrashHack.General;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Control extends Screen {

	public static final int TOTALBUTTONS	= 8;
	public static final int	ANYTHING 		= 0;
	public static final int UP 				= 1;
	public static final int DOWN 			= 2;
	public static final int LEFT 			= 3;
	public static final int RIGHT 			= 4;
	public static final int A 				= 5;
	public static final int B 				= 6;
	public static final int PAD 			= 7;
	
	//public static ArrayList<UIButton> buttons = new ArrayList<UIButton>(TOTALBUTTONS);
	public int addUIButton (Sprite _sprite, int _minSizeX, int _minSizeY, int _maxSizeX, int _maxSizeY);
	public boolean isPressed (int _id);
	public void press (int _id);
	public void unpress (int _id);
	
	public void loadArt();
	
	
}
