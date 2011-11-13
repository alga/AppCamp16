package lt.appcamp.appcamp16;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
 
public class Splash extends Activity {

    protected int _splashTime = 4000; // time to display the splash screen in ms
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        new Handler().postDelayed(new Runnable(){
            public void run() {
                finish();
                startActivity(new Intent("lt.appcamp.appcamp16.Antras"));
            }
        }, _splashTime);
    }

}
