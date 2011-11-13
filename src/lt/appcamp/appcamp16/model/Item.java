package lt.appcamp.appcamp16.model;

import android.graphics.Bitmap;
import lt.appcamp.appcamp16.services.HttpRetriever;

public class Item {
    public Integer id;
    public String title;
    public String url;
    public Integer userId;
    public String photoUrl;
    public String thumbUrl;
    public Bitmap thumbBitmap;
    public Bitmap photoBitmap;
    public String size;
    public Category category;

    public Bitmap getThumbBitmap() {
        if(thumbBitmap == null) {
            thumbBitmap = new HttpRetriever().retrieveBitmap(thumbUrl);
        }
        return thumbBitmap;
    }
    
    public Bitmap getPhotoBitmap() {
        if(photoBitmap == null) {
            photoBitmap = new HttpRetriever().retrieveBitmap(photoUrl);
        }

        return photoBitmap;
    }
    
}
