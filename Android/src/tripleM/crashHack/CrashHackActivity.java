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
        CrashHack ch = new CrashHack(ca);
        initialize(ch, false);
        
        this.graphics.getView().setOnTouchListener(ca);
        this.graphics.getView().setKeepScreenOn(true);    
        ((ControlAndroid) (CrashHack.ctrl)).setupAPI();
        
    }
}