package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.R;
import lt.appcamp.appcamp16.model.Category;

import java.util.ArrayList;

public class CategoriesSeeker {
    public static final int CATEGORY_DRESSES = 10; 
    public static final int CATEGORY_BAGS = 19; 
    public static final int CATEGORY_SHOES = 16; 
    public static final int CATEGORY_TROUSERS = 9; 
    public static final int CATEGORY_JACKETS = 172; 
    public static final int CATEGORY_TOPS = 12; 
    public static final int CATEGORY_BLAZERS = 8; 
    public static final int CATEGORY_PANTIES = 120; 
    public static final int CATEGORY_SKIRTS = 11; 
    public static final int CATEGORY_HATS = 88; 
    
    public static final ArrayList<Category> categories = new ArrayList<Category>();
    
    static {
        categories.add(new Category("Suknelės", CATEGORY_DRESSES, 44, 200, R.drawable.c4));
        categories.add(new Category("Rankinės", CATEGORY_BAGS, 36, 300, R.drawable.c8));
        categories.add(new Category("Bateliai", CATEGORY_SHOES, 52, 400, R.drawable.c7));
        categories.add(new Category("Kelnės", CATEGORY_TROUSERS, 32, 300, R.drawable.c3));
        categories.add(new Category("Striukės", CATEGORY_JACKETS, 56, 500, R.drawable.c2));
        categories.add(new Category("Palaidinės", CATEGORY_TOPS, 19, 200, R.drawable.c11));
        categories.add(new Category("Švarkeliai", CATEGORY_BLAZERS, 32, 400, R.drawable.c13));
        categories.add(new Category("Triusikėliai", CATEGORY_PANTIES, 18, 100, R.drawable.c10));
        categories.add(new Category("Sijonai", CATEGORY_SKIRTS, 22, 300, R.drawable.c12));
        categories.add(new Category("Kepurės", CATEGORY_HATS, 21, 200, R.drawable.c9));
    }
    
    public ArrayList<Category> find() {
        return categories;
    }
}
