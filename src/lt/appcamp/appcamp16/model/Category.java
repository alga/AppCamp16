package lt.appcamp.appcamp16.model;

import android.widget.ImageView;
import lt.appcamp.appcamp16.R;

import java.util.ArrayList;

public class Category {
    String title;
    Integer id;
    Integer view = null;

    public Category(String title, Integer id) {
        this.title = title;
        this.id = id;
    }
    
    public Category(String title, Integer id, Integer draw) {
        this.title = title;
        this.id = id;
        this.view = draw;
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
    
    public Integer getView() {
        return this.view;
    }
    
    public void setView(Integer view) {
        this.view = view;
    }
}
