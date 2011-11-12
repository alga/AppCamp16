package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.model.Item;

import java.util.ArrayList;

public class ItemSeeker {
    private static final String ITEM_SEARCH_PATH = "get_items";
    private static final String API_URL = "http://http://www.manodrabuziai.lt/api/";
    public ArrayList<Item> items = null;
    
    public ArrayList<Item> find() {
        String url = ITEM_SEARCH_PATH + API_URL;
        
        String xml = new HttpRetriever().retrieve(url);
        ArrayList<Item> items = new XmlParser().parseItemResponse(xml);
        return items;
    }
}
