package lt.appcamp.appcamp16.model;

import android.widget.ImageView;

import java.util.ArrayList;

public class Category {
    String title;
    Integer id;
    ImageView view = null;

    public Category(String title, Integer id) {
        this.title = title;
        this.id = id;
    }
    
    public Category(String title, Integer id, ImageView view) {
        this.title = title;
        this.id = id;
        this.view = view;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title; 
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public ImageView getView() {
        return this.view;
    }
    
    public void setView(ImageView view) {
        this.view = view;
    }
}
