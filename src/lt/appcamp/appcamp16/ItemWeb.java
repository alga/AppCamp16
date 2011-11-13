package lt.appcamp.appcamp16;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;

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
                
        webView.setPictureListener(new WebPictureListener());
    }
    
    class WebPictureListener implements PictureListener {

        @Override
        public void onNewPicture(WebView view, Picture arg1) {
            ItemWeb.this.webView.setVisibility(View.VISIBLE);
            ItemWeb.this.progress.dismiss();             
        }    
    } 


}
