package tripleM.CrashHack;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	public static void main(String[] args) {
		new JoglApplication(
				new CrashHack(),
				"Titulo",
				480, 320,
				false);
	}

}
