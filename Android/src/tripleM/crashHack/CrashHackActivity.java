package tripleM.crashHack;

import tripleM.CrashHack.General.CrashHack;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class CrashHackActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ControlAndroid ca = new ControlAndroid();
        initialize(new CrashHack(ca), false);
        this.graphics.getView().setOnTouchListener(ca);
        
    }
}