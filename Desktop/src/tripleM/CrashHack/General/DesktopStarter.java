package tripleM.CrashHack.General;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 320;
	
	public static void main(String[] args) {
		
		ControlDesktop ctrl = new ControlDesktop();
		CrashHack ch = new CrashHack(ctrl);
		
		
		new JoglApplication(
				ch,
				"Crash Hack",
				GAME_WIDTH,
				GAME_HEIGHT,
				false);
		
		CrashHack.desktop_regControl();
		
	}

}
