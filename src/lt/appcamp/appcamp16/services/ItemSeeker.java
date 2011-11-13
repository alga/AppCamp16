package lt.appcamp.appcamp16.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import lt.appcamp.appcamp16.model.Category;
import lt.appcamp.appcamp16.model.Item;
import android.content.Context;
import android.util.Log;

public class ItemSeeker {
    public static final String TAG = "Trecias";
    private static final String ITEM_SEARCH_PATH = "get_items";
    private static final String API_URL = "http://www.manodrabuziai.lt/api/";
    public ArrayList<Item> items = null;
    public Integer categoryId;
    public Category category;

    public ItemSeeker(Integer categoryId) {
        this.categoryId = categoryId;

        for (Category c : CategoriesSeeker.categories) {
            if (c.getId().equals(categoryId)) {
                category = c;
                break;
            }
        }
    }

    public ArrayList<Item> find(Context context) {
        String xml = getXml(context);
        items = new XmlParser().parseItemResponse(xml);
        postProcessItems();
        return items;
    }

    private void postProcessItems() {
        ArrayList<Item> result = new ArrayList<Item>();
        
        for (Item item : items) {
            item.category = this.category;
            if (item.photoUrl != null && item.thumbUrl != null && item.userId != null && item.userId.compareTo(9149) != 0) {
                result.add(item);
            }
        }
        
        items = result;
    }
    
    private String getXml(Context context) {
        String xml = getCachedData(context);
        if(xml == null) {
            String url = API_URL + ITEM_SEARCH_PATH;
    
            url += "?order=like_count";
    
            if (categoryId != null) {
                url += "&catalog_id=" + categoryId.toString();
            }
    
            Log.i(TAG, "url = " + url);
            xml = new HttpRetriever().retrieve(url);
            cacheData(context, xml);
        }
        return xml;
    }

    
    private String getCachedData(Context context) {
        try {
            String filename = "get_items:" + categoryId.toString();
            File cacheDir = context.getCacheDir();
            File cacheFile = new File(cacheDir, filename);

            if(!cacheFile.exists()) {
                return null;
            }
            
            Long modBefore = new Date().getTime() - cacheFile.lastModified();
            if(modBefore > 3600 * 1000) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
            String line;
            String content = "";
            while((line = reader.readLine()) != null) {
                content = content.concat(line);
            }

            return content;

        } catch(Exception e) {
            return null;
        }
    }
    
    private void cacheData(Context context, String xml) {
        try {
            String filename = "get_items:" + categoryId.toString();
            File cacheDir = context.getCacheDir();
            File cacheFile = new File(cacheDir, filename);

            cacheFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(cacheFile);
            stream.write(xml.getBytes());
            stream.close();
        } catch (Exception e) {
        }
    }
}
