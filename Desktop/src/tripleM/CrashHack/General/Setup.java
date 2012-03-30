package tripleM.CrashHack.General;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Setup {
	private static final String PROPERTIES_FILE = "data/CrashHack.properties";
	private static Properties properties = null;
	public static final String fatfingers = "fatfingers";
	
	public static void load () {
		if (properties != null) return;
		properties = new Properties();
		FileHandle fh = Gdx.files.internal(PROPERTIES_FILE);
		InputStream inStream = fh.read();
		try {
			properties.load(inStream);
			inStream.close();
		} catch (IOException e) {
			Gdx.app.log("Control", "Config load failed!!");
			Gdx.app.exit();
		}
	}
	
	public static int getConfigInt (String _property, int _fallback)
	{
		if (properties == null) Setup.load();
		String ret = properties.getProperty(_property);
		if (ret == null) return _fallback;
		return Integer.parseInt(ret);
	}
	
	public static float getConfigFloat (String _property, float _fallback)
	{
		if (properties == null) Setup.load();
		String ret = properties.getProperty(_property);
		if (ret == null) return _fallback;
		return Float.parseFloat(ret);
	}
	
	public static String getConfigString (String _property, String _fallback)
	{
		if (properties == null) Setup.load();
		String ret = properties.getProperty(_property);
		if (ret == null) return _fallback;
		return ret;
	}
	
}
