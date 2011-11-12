package lt.appcamp.appcamp16;

import lt.appcamp.appcamp16.ui.CoverFlow;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.AdapterView;


public class Antras extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antras);

        //CoverFlow gal = (CoverFlow) findViewById(R.id.category_gallery);
        Gallery gal = (Gallery) findViewById(R.id.category_gallery);
        gal.setAdapter(new ImageAdapter(this));
        gal.setOnItemClickListener(new ClickListener(this));

        //gal.setMaxRotationAngle(0);
        //gal.setMaxZoom(-100);

    }

    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
                R.drawable.c6,
                R.drawable.c7,
                R.drawable.c8,
                R.drawable.c9,
                R.drawable.c10,
                R.drawable.c11,
                R.drawable.c12,
                R.drawable.c13
        };

        public ImageAdapter(Context c) {
            mContext = c;
//            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.Trecias);
//            mGalleryItemBackground = attr.getResourceId(
//                    R.styleable.HelloGallery_android_galleryItemBackground, 0);
//            attr.recycle();
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
            imageView.setLayoutParams(new Gallery.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }
    }

    private class ClickListener implements AdapterView.OnItemClickListener {

        Context c;

        public ClickListener(Context c) {
            this.c = c;
        }

        public void  onItemClick(AdapterView<?>  parent, View  v,
                                 int position, long id) {

            Intent intent = new Intent(c, Trecias.class);
            intent.putExtra("category_index", position);
            startActivity(intent);

        }
   }

}
