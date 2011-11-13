package lt.appcamp.appcamp16.services;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import lt.appcamp.appcamp16.R;
import lt.appcamp.appcamp16.model.Item;

import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter {
    protected Context context;
    protected ArrayList<Item> items;
    public PhotoAdapter(Context c, int category_id) {
        this.context = c;

        TypedArray attr = context.obtainStyledAttributes(R.styleable.ProductsGallery);
        attr.recycle();

        items = new ItemSeeker(category_id).find(context);
    }


    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ImageView imageView = new ImageView(context);
        final Item item = items.get(i);
        
        Bitmap bitmap = item.thumbBitmap;
        if(bitmap == null) {
        
            final Handler handler = new Handler() {
                public void handleMessage(Message message) {
                    imageView.setImageBitmap((Bitmap)message.obj);
                }
            };
            
            Thread thread = new Thread() {
                public void run() {
                    item.getThumbBitmap();
                    if(item.thumbBitmap == null) {
                        item.thumbBitmap = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.noitem)).getBitmap();
                    }
                    
                    Message message = handler.obtainMessage(1, item.thumbBitmap);
                    handler.sendMessage(message);
                }
            };

            thread.start();
            bitmap = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.noitem)).getBitmap();
        }

        imageView.setImageBitmap(bitmap);
        
        Integer x = 300;
        Integer y = x * (bitmap.getHeight() / bitmap.getWidth());
        
        if(y > 300) {
            y = 300;
            x = 300 * (bitmap.getWidth() / bitmap.getHeight());
        }
        
        imageView.setLayoutParams(new Gallery.LayoutParams(x, y));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
