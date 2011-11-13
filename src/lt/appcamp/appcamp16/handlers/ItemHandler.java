package lt.appcamp.appcamp16.handlers;

import java.util.ArrayList;

import lt.appcamp.appcamp16.model.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemHandler extends DefaultHandler {
    private StringBuffer buffer = new StringBuffer();
    private ArrayList<Item> items;
    private Item item;

    private boolean thumbnail = false;
    private String thumbnailType;
    private String tempString = null;
    private boolean isMain = false;
    private boolean photo = false;
    

    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        buffer.setLength(0);

        if(localName.equals("items")) {
            items = new ArrayList<Item>();
        } else if(localName.equals("item")) {
            item = new Item();
            thumbnail = false;
            photo = false;
            isMain = false;
        } else if(localName.equals("photo")) {
            photo = true;
        } else if(localName.equals("thumbnail")) {
            thumbnail = true;
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("item")) {
            if(item.photoUrl != null && !item.photoUrl.isEmpty()) {
                items.add(item);
            }
        } else if(localName.equals("title")) {
            item.title = buffer.toString();
        } else if (localName.equals("id")) {
            item.id = Integer.parseInt(buffer.toString());
        } else if (localName.equals("size")) {
            item.size = buffer.toString();
        } else if(localName.equals("url")) {
            if (thumbnail && isMain && thumbnailType.equals("thumb130")) {
                item.thumbUrl = buffer.toString();
            } else if (photo) {
                tempString = buffer.toString();
            } else {
                item.url = buffer.toString();
            }
        } else if(localName.equals("type")) {
            if (thumbnail) {
                thumbnailType = buffer.toString();
            }
        } else if(localName.equals("is_main")) {
            isMain = buffer.toString().equals("true");
            if (isMain) {
                item.photoUrl = tempString;
            }
        } else if(localName.equals("thumbnail")) {
            thumbnail = true;
        }
    }
    
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }

}
