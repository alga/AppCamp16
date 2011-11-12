package lt.appcamp.appcamp16;

import lt.appcamp.appcamp16.services.PhotoAdapter;
import lt.appcamp.appcamp16.ui.CoverFlow;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class Trecias extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecias);

        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.gallery);
        
//        PhotoAdapter coverImageAdapter =  new PhotoAdapter(this);
//        coverFlow.setAdapter(coverImageAdapter);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(imageAdapter);
        
        coverFlow.setSpacing(-25);
        coverFlow.setSelection(4, true);
        coverFlow.setAnimationDuration(1000);
        
        coverFlow.setOnItemClickListener(new ClickListener(this));
    }
    
    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.k1,
                R.drawable.k2,
                R.drawable.k3,
                R.drawable.k4,
                R.drawable.k5,
                R.drawable.k6
        };

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.ProductsGallery);
            mGalleryItemBackground = attr.getResourceId(
                    R.styleable.ProductsGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }
    }
    
    private class ClickListener implements AdapterView.OnItemClickListener {

        private Animation grow     = null;
        private View      lastView = null; 

        public ClickListener(Context c) {
             grow = AnimationUtils.loadAnimation(c, R.anim.grow);
        }

        public void  onItemClick(AdapterView<?>  parent, View  v, int position, long id)         {

            // Shrink the view that was zoomed 
            try { if (null != lastView) lastView.clearAnimation();
            } catch (Exception clear) { }

            // Zoom the new selected view
            try { v.startAnimation(grow); } catch (Exception animate) {}

            // Set the last view so we can clear the animation 
            lastView = v;
        }

   }
}