package lt.appcamp.appcamp16;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.services.CategoriesSeeker;
import lt.appcamp.appcamp16.services.PhotoAdapter;
import lt.appcamp.appcamp16.ui.CoverFlow;
import lt.appcamp.appcamp16.utils.WasteCalculator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Trecias extends Activity
{
    private CoverFlow coverFlow;
    private Integer category_id;

    public static final String CATEGORY_PARAM = "category_id";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecias);
        
        coverFlow = (CoverFlow) findViewById(R.id.gallery);
        Button buttonMore = (Button) findViewById(R.id.buttonMore);
        
        category_id = getIntent().getExtras().getInt(CATEGORY_PARAM);
        
        Log.i("Trecias","got category " + new Integer(category_id).toString());

        coverFlow.setSpacing(-25);
        coverFlow.setSelection(4, true);
        coverFlow.setAnimationDuration(1000);
        
        coverFlow.setOnItemClickListener(new ClickListener());
        coverFlow.setOnItemSelectedListener(new SelectListener());
        buttonMore.setOnClickListener(new ButtonMoreClickListener());
        
        
        findViewById(R.id.preview).setOnClickListener(new PreviewClickListener(this));
        TextView categoryTitleView = (TextView) findViewById(R.id.categoryTitle);

        categoryTitleView.setText((new CategoriesSeeker()).titleByIndex(category_id));
     
        coverFlow.setSelection(1);
        
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
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
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
                
        public void onItemClick(AdapterView<?>  parent, View  v, int position, long id)         {
            Item item = (Item)parent.getItemAtPosition(position);
            
            LoadSinglePhotoTask task = new LoadSinglePhotoTask();
            task.setShowDialog(item.photoBitmap == null);
            task.execute(item);
        }
        
        class LoadSinglePhotoTask extends AsyncTask<Object, Void, Void> {
            private ProgressDialog progress;
            private boolean showDialog = true;
            private Bitmap bitmap = null;
            
            @Override
            protected void onPreExecute() {
                if (showDialog) {
                    this.progress = ProgressDialog.show(Trecias.this, "", "Loading...", true);
                }
                ImageView imageView = (ImageView)findViewById(R.id.previewImage);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
            }

            @Override
            protected Void doInBackground(Object... objs) {
                Item item = (Item) objs[0];
                
                bitmap = item.getPhotoBitmap();
                return null;
            }

            @Override
            protected void onPostExecute(Void adapter) {
                ImageView imageView = (ImageView)findViewById(R.id.previewImage);
                View preview = findViewById(R.id.preview);

                imageView.setImageBitmap(bitmap);

                Animation fadeInAnimation = AnimationUtils.loadAnimation(Trecias.this, R.anim.fade_in);

                preview.startAnimation(fadeInAnimation);                
                preview.setVisibility(View.VISIBLE);
                if (progress != null) {
                    progress.dismiss();
                }
            }


            public void setShowDialog(boolean showDialog) {
                this.showDialog = showDialog;
            }
       }
   }
    
    private class ButtonMoreClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Item item = (Item) coverFlow.getSelectedItem();
            
            Intent intent = new Intent(Trecias.this, ItemWeb.class);
            intent.putExtra(ItemWeb.URL_PARAM, item.url);
            startActivity(intent);            
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
