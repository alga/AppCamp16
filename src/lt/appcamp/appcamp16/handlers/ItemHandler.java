package lt.appcamp.appcamp16.handlers;

import lt.appcamp.appcamp16.model.Item;
import lt.appcamp.appcamp16.model.Photo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ItemHandler extends DefaultHandler {
    private StringBuffer buffer = new StringBuffer();
    private ArrayList<Item> items;
    private Item item;
    private Photo photo = null;
    private boolean thumbnail = false;
    private String thumbnailUrl;
    private String thumbnailType;

    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        buffer.setLength(0);

        if(localName.equals("items")) {
            items = new ArrayList<Item>();
        } else if(localName.equals("item")) {
            item = new Item();
        } else if(localName.equals("photo")) {
            photo = new Photo();
        } else if(localName.equals("thumbnail")) {
            thumbnail = true;
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("item")) {
            items.add(item);
        } else if(localName.equals("title")) {
            item.title = buffer.toString();
        } else if(localName.equals("url")) {
            if(thumbnail == true) {
                thumbnailUrl = buffer.toString();
                setThumb();
            } else if(photo != null) {
                photo.setOriginalUrl(buffer.toString());
            }
        } else if(localName.equals("type")) {
            thumbnailType = buffer.toString();
            setThumb();
        } else if(localName.equals("photo")) {
            item.photos.add(photo);
        }
    }
    
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }

    protected void setThumb() {
        if(thumbnail == true && thumbnailType != null && thumbnailType.equals("thumb130")) {
            photo.setThumbUrl(thumbnailUrl);
            thumbnail = false;
        }
    }
}