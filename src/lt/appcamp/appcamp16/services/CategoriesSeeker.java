package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.model.Category;

import java.util.ArrayList;

public class CategoriesSeeker {
    public static final ArrayList<Category> categories = new ArrayList<Category>();
    static {
        categories.add(new Category("Suknelės", 10));
        categories.add(new Category("Rankinės", 19));
        categories.add(new Category("Bateliai", 16));
        categories.add(new Category("Kelnės", 9));
        categories.add(new Category("Striukės", 172));
        categories.add(new Category("Palaidinės", 12));
        categories.add(new Category("Megztinukai", 13));
        categories.add(new Category("Švarkeliai", 8));
        categories.add(new Category("Triusikėliai", 120));
    }
    
    public ArrayList<Category> find() {
        return categories;
    }
}
