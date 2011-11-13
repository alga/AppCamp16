package lt.appcamp.appcamp16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ItemWeb extends Activity {
    private ProgressDialog progress;
    private WebView webView;

    public static final String URL_PARAM = "url";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_web);
        
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        webView.loadUrl(getIntent().getExtras().getString(URL_PARAM));
        
        progress = ProgressDialog.show(this, "", "Loading...", true);
        
        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable(){
            public void run() {
                ItemWeb.this.webView.setVisibility(View.VISIBLE);
                ItemWeb.this.progress.dismiss();             
            }
         }, 3000);


    }

}
