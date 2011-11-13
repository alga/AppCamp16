package lt.appcamp.appcamp16;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.model.Waste;
import lt.appcamp.appcamp16.services.CategoriesSeeker;
import lt.appcamp.appcamp16.services.PhotoAdapter;
import lt.appcamp.appcamp16.ui.CoverFlow;
import lt.appcamp.appcamp16.utils.WasteCalculator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

public class Trecias extends Activity
{
    CoverFlow coverFlow;
    public static final String CATEGORY_PARAM = "category_id";
    Integer category_id;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecias);
        
        coverFlow = (CoverFlow) findViewById(R.id.gallery);

        category_id = getIntent().getExtras().getInt(CATEGORY_PARAM);
        
        Log.i("Trecias","got category " + new Integer(category_id).toString());

        coverFlow.setSpacing(-25);
        coverFlow.setSelection(4, true);
        coverFlow.setAnimationDuration(1000);
        
        coverFlow.setOnItemClickListener(new ClickListener(this));
        coverFlow.setOnItemSelectedListener(new SelectListener(this));
        
        findViewById(R.id.preview).setOnClickListener(new PreviewClickListener(this));
        TextView categoryTitleView = (TextView) findViewById(R.id.categoryTitle);

        categoryTitleView.setText((new CategoriesSeeker()).titleByIndex(category_id));
     
        new LoadPhotoAdapter().execute();
    }

    class LoadPhotoAdapter extends AsyncTask<Void, Void, PhotoAdapter> {
        ProgressDialog progress;
        protected void onPreExecute() {
            this.progress = ProgressDialog.show(Trecias.this, "", "Loading...", true);
        }

        @Override
        protected PhotoAdapter doInBackground(Void... voids) {
            return new PhotoAdapter(Trecias.this, category_id);
        }

        protected void onPostExecute(PhotoAdapter adapter) {
            coverFlow.setAdapter(adapter);
            progress.dismiss();
            coverFlow.setSelection(1);
        }
   }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && findViewById(R.id.preview).getVisibility() == View.VISIBLE) {
            
            findViewById(R.id.preview).setVisibility(View.GONE);
            
            return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }

    private class SelectListener implements AdapterView.OnItemSelectedListener {

        public SelectListener(Context c) {
            
        }
        
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                long id) {
            // TODO Auto-generated method stub
            Item item = (Item) parent.getSelectedItem();
            Waste waste = WasteCalculator.calculate(item);
            
            TextView wasteInfo = (TextView) findViewById(R.id.waterInfo);
            wasteInfo.setText(waste.waterString());
            
            TextView carbonInfo = (TextView) findViewById(R.id.carbonInfo);
            carbonInfo.setText(waste.carbonString());
            
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(item.title);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
            
        }

        
    }
    
    private class ClickListener implements AdapterView.OnItemClickListener {

        Context c;
        
        public ClickListener(Context c) {
            this.c = c;
        }

        public void  onItemClick(AdapterView<?>  parent, View  v, int position, long id)         {
            Item item = (Item)parent.getSelectedItem();
            
            View preview = findViewById(R.id.preview);
            ImageView imageView = (ImageView)findViewById(R.id.previewImage);
            
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageBitmap(item.getPhotoBitmap());
            
        
            Animation fadeInAnimation = AnimationUtils.loadAnimation(c, R.anim.fade_in);

            preview.startAnimation(fadeInAnimation);
            preview.setVisibility(View.VISIBLE);
            
        }
   }
    
   private class PreviewClickListener implements AdapterView.OnClickListener {

        Context c;
        
        public PreviewClickListener(Context c) {
            this.c = c;
        }

        @Override
        public void onClick(View v) {
            View preview = findViewById(R.id.preview);
            
            Animation fadeOutAnimation = AnimationUtils.loadAnimation(c, R.anim.fade_out);
           
            fadeOutAnimation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationEnd(Animation arg0) {
                    findViewById(R.id.preview).setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub
                    
                }

                @Override
                public void onAnimationStart(Animation animation) {
                    // TODO Auto-generated method stub
                    
                }
            });
            
            preview.startAnimation(fadeOutAnimation);
    
        }
   }
}
