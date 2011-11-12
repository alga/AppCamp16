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
        categories.add(new Category("Suknelės", CATEGORY_DRESSES, 44.0, 0.212, R.drawable.c4));
        categories.add(new Category("Rankinės", CATEGORY_BAGS, 36.0, 0.354, R.drawable.c8));
        categories.add(new Category("Bateliai", CATEGORY_SHOES, 52.0, 0.477, R.drawable.c7));
        categories.add(new Category("Kelnės", CATEGORY_TROUSERS, 32.0, 0.334, R.drawable.c3));
        categories.add(new Category("Striukės", CATEGORY_JACKETS, 56.0, 0.513, R.drawable.c2));
        categories.add(new Category("Palaidinės", CATEGORY_TOPS, 19.0, 0.265, R.drawable.c11));
        categories.add(new Category("Švarkeliai", CATEGORY_BLAZERS, 32.0, 0.412, R.drawable.c13));
        categories.add(new Category("Triusikėliai", CATEGORY_PANTIES, 18.0, 0.187, R.drawable.c10));
        categories.add(new Category("Sijonai", CATEGORY_SKIRTS, 22.0, 0.354, R.drawable.c12));
        categories.add(new Category("Kepurės", CATEGORY_HATS, 21.0, 0.223, R.drawable.c9));
    }
    
    public ArrayList<Category> find() {
        return categories;
    }

    public int idByIndex(int index) {
        return categories.get(index).getId().intValue();
    }
}
