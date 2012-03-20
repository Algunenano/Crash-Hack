package tripleM.CrashHack.General;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	public static void main(String[] args) {
		new JoglApplication(
				new CrashHack(),
				"Crash Hack",
				CrashHack.GAME_WIDTH,
				CrashHack.GAME_HEIGHT,
				false);
	}

}
