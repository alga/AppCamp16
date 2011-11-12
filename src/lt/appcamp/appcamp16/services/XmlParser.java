package lt.appcamp.appcamp16.services;

import lt.appcamp.appcamp16.handlers.ItemHandler;
import lt.appcamp.appcamp16.model.Item;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.ArrayList;

public class XmlParser {
    private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        return reader;
    }
    
    public ArrayList<Item> parseItemResponse(String xml) {
        try {
            XMLReader reader = initializeReader();
            ItemHandler handler = new ItemHandler();
            
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new StringReader(xml)));

            return handler.getItems();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
