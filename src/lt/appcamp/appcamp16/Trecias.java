package lt.appcamp.appcamp16;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.services.PhotoAdapter;
import lt.appcamp.appcamp16.ui.CoverFlow;
import lt.appcamp.appcamp16.utils.WasteCalculator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class Trecias extends Activity
{
    public static final String CATEGORY_PARAM = "category_id";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecias);

        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.gallery);

        int category_id = getIntent().getExtras().getInt(CATEGORY_PARAM);
        Log.i("Trecias","got category " + new Integer(category_id).toString());
        PhotoAdapter coverImageAdapter =  new PhotoAdapter(this, category_id);
        coverFlow.setAdapter(coverImageAdapter);
        
        coverFlow.setSpacing(-25);
        coverFlow.setSelection(4, true);
        coverFlow.setAnimationDuration(1000);
        
        coverFlow.setOnItemClickListener(new ClickListener(this));
        coverFlow.setOnItemSelectedListener(new SelectListener(this));
        
        //ProgressDialog dialog = new ProgressDialog(getApplicationContext());
        //dialog.show();
        //ProgressDialog.show(this, null, "Loading...", true);
        
        findViewById(R.id.preview).setOnClickListener(new PreviewClickListener(this));
        
        TextView categoryTitleView = (TextView) findViewById(R.id.categoryTitle);
        categoryTitleView.setText("Kategorijos pavadinimas");
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
            ProgressDialog progressDialog = null;
            if (item.photoBitmap == null) {
                progressDialog = ProgressDialog.show(Trecias.this, null, "Loading...", true);                
            }
            
            ProgressDialog progressDialog = ProgressDialog.show(Trecias.this, null, "Loading...", true);
            

            ImageView imageView = (ImageView)findViewById(R.id.previewImage);
            View preview = findViewById(R.id.preview);

            imageView.setScaleType(ImageView.ScaleType.CENTER);
            
            
            new Thread() {
                public void run() {
                    ImageView imageView = (ImageView)findViewById(R.id.previewImage);

                    imageView.setImageBitmap(item.getPhotoBitmap());
                    
                    
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(c, R.anim.fade_in);

                    if (showLoader) {
                        Trecias.this.stopLoading();
                    }
                    preview.startAnimation(fadeInAnimation);
                    preview.setVisibility(View.VISIBLE);

                }
                
            }.start();
            
            
        }
        
        private class ItemClickThread implements Runnable {

            public void Create() {
                
            }
            @Override
            public void run() {
                // TODO Auto-generated method stub
                
            }
        
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