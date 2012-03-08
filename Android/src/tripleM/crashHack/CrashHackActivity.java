package tripleM.crashHack;

import tripleM.CrashHack.CrashHack;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class CrashHackActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new CrashHack(), false);
    }
}