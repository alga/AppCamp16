package lt.appcamp.appcamp16;

import lt.appcamp.appcamp16.services.CategoriesSeeker;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


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
        private CategoriesSeeker categories;

        public ImageAdapter(Context c) {
            mContext = c;
            categories = new CategoriesSeeker();
        }

        public int getCount() {
            return categories.find().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(categories.find().get(position).getView());
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

            CategoriesSeeker cats = new CategoriesSeeker();
            Intent intent = new Intent(c, Trecias.class);
            intent.putExtra(Trecias.CATEGORY_PARAM, cats.idByIndex(position));
            startActivity(intent);

        }
   }

}
