package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.model.Category;
import lt.appcamp.appcamp16.model.Item;

import java.util.ArrayList;

public class ItemSeeker {
    private static final String ITEM_SEARCH_PATH = "get_items";
    private static final String API_URL = "http://www.manodrabuziai.lt/api/";
    public ArrayList<Item> items = null;
    public Integer categoryId;
    public Category category;
    
    public ItemSeeker() {
        
    }
    
    public ItemSeeker(Integer categoryId) {
        this.categoryId = categoryId;
        
        for (Category c : CategoriesSeeker.categories) {
            if (c.getId().equals(categoryId)) {
                category = c;
                break;
            }            
        }
    }
    
    public ArrayList<Item> find() {
        String url = API_URL + ITEM_SEARCH_PATH;
        
        url += "?order=like_count";
        
        if (categoryId != null) {
            url += "&category_id=" + categoryId.toString();
        }
        
        String xml = new HttpRetriever().retrieve(url);
        ArrayList<Item> items = new XmlParser().parseItemResponse(xml);
        
        postProcessItems(items);
        
        return items;
    }
    
    private void postProcessItems(ArrayList<Item> items) {
        for (Item item : items) {
            item.category = this.category; 
        }
    }
}
