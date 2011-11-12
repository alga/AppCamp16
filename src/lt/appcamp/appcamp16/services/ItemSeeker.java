package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.model.Item;

import java.util.ArrayList;

public class ItemSeeker {
    private static final String ITEM_SEARCH_PATH = "get_items";
    private static final String API_URL = "http://www.manodrabuziai.lt/api/";
    public ArrayList<Item> items = null;
    public Integer category;
    
    public ItemSeeker() {
        
    }
    
    public ItemSeeker(Integer category) {
        this.category = category;
    }
    
    public ArrayList<Item> find() {
        String url = API_URL + ITEM_SEARCH_PATH;

        if(category != null) {
            url = url + "?category_id=" + category.toString();
        }
        
        String xml = new HttpRetriever().retrieve(url);
        ArrayList<Item> items = new XmlParser().parseItemResponse(xml);
        return items;
    }
}
