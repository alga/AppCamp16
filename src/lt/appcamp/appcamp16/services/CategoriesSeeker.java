package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.R;
import lt.appcamp.appcamp16.model.Category;

import java.util.ArrayList;

public class CategoriesSeeker {
    public static final ArrayList<Category> categories = new ArrayList<Category>();
    static {
        categories.add(new Category("Suknelės", 10, R.drawable.c4));
        categories.add(new Category("Rankinės", 19, R.drawable.c8));
        categories.add(new Category("Bateliai", 16, R.drawable.c7));
        categories.add(new Category("Kelnės", 9, R.drawable.c3));
        categories.add(new Category("Striukės", 172, R.drawable.c2));
        categories.add(new Category("Palaidinės", 12, R.drawable.c11));
        categories.add(new Category("Švarkeliai", 8, R.drawable.c13));
        categories.add(new Category("Triusikėliai", 120, R.drawable.c10));
        categories.add(new Category("Sijonai", 11, R.drawable.c12));
        categories.add(new Category("Kepurės", 88, R.drawable.c9));
    }
    
    public ArrayList<Category> find() {
        return categories;
    }
}
