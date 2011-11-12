package lt.appcamp.appcamp16.services;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
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
        ImageView imageView = new ImageView(context);
        Item item = items.get(i);
        Bitmap bitmap = new HttpRetriever().retrieveBitmap(item.thumbUrl);
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }
}
