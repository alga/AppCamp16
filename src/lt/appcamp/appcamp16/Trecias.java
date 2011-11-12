package lt.appcamp.appcamp16;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.services.PhotoAdapter;
import lt.appcamp.appcamp16.ui.CoverFlow;
import lt.appcamp.appcamp16.utils.WasteCalculator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class Trecias extends Activity
{
    CoverFlow coverFlow;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecias);

        coverFlow = (CoverFlow) findViewById(R.id.gallery);
        
        coverFlow.setSpacing(-25);
        coverFlow.setSelection(4, true);
        coverFlow.setAnimationDuration(1000);
        
        coverFlow.setOnItemClickListener(new ClickListener(this));
        coverFlow.setOnItemSelectedListener(new SelectListener(this));
        
        findViewById(R.id.preview).setOnClickListener(new PreviewClickListener(this));
        
        new LoadPhotoAdapter().execute();
    }

    class LoadPhotoAdapter extends AsyncTask<Void, Void, PhotoAdapter> {
        ProgressDialog progress;
        protected void onPreExecute() {
            this.progress = ProgressDialog.show(Trecias.this, "", "Loading...", true);
        }

        @Override
        protected PhotoAdapter doInBackground(Void... voids) {
            return new PhotoAdapter(Trecias.this);
        }

        protected void onPostExecute(PhotoAdapter adapter) {
            coverFlow.setAdapter(adapter);
            progress.dismiss();
        }
    }

    private class SelectListener implements AdapterView.OnItemSelectedListener {

        public SelectListener(Context c) {
            
        }
        
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                long id) {
            // TODO Auto-generated method stub
            Item item = (Item) parent.getSelectedItem();
            
            TextView wasteInfo = (TextView) findViewById(R.id.wasteInfo);
            wasteInfo.setText(WasteCalculator.calculate(item).toString());
            
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