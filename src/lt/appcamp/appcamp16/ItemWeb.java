package lt.appcamp.appcamp16;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ItemWeb extends Activity {

    public static final String URL_PARAM = "url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_web);
        
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(getIntent().getExtras().getString(URL_PARAM));
    }

}
