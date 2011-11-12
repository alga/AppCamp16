package lt.appcamp.appcamp16.model;

import android.graphics.Bitmap;
import lt.appcamp.appcamp16.services.HttpRetriever;

public class Item {
    public Integer id;
    public String title;
    public String url;
    public String photoUrl;
    public String thumbUrl;
    public String size;
    
    /* filled in not from XML */
    public Category category;
    public Bitmap bitmap;

    public Bitmap getBitmap() {
        if(bitmap == null) {
            bitmap = new HttpRetriever().retrieveBitmap(thumbUrl);
        }
        return bitmap;
    }
    
}
