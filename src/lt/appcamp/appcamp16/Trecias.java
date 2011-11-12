package lt.appcamp.appcamp16;

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
import android.util.Log;

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
        
        findViewById(R.id.preview).setOnClickListener(new PreviewClickListener(this));
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
            //wasteInfo.setText(WasteCalculator.calculate(item).toString());
            wasteInfo.setText("111");
            
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
            TextView wasteInfo = (TextView)findViewById(R.id.wasteInfo);
            
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageBitmap(item.getPhotoBitmap());
            
            
            Animation fadeInAnimation = AnimationUtils.loadAnimation(c, R.anim.fade_in);
           
            wasteInfo.setText(WasteCalculator.calculate(item).toString());
            
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