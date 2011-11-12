package lt.appcamp.appcamp16.model;

import android.graphics.Bitmap;
import lt.appcamp.appcamp16.services.HttpRetriever;

public class Item {
    public String title;
    public String url;
    public String photoUrl;
    public String thumbUrl;
    public Bitmap bitmap;

    public Bitmap getBitmap() {
        if(bitmap == null) {
            bitmap = new HttpRetriever().retrieveBitmap(thumbUrl);
        }
        return bitmap;
    }
    
}
