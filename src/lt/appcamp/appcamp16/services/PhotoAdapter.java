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
    public PhotoAdapter(Context c) {
        this.context = c;
        this.items = new ItemSeeker().find();
        TypedArray attr = context.obtainStyledAttributes(R.styleable.ProductsGallery);
        attr.recycle();
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
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
