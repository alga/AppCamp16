package lt.appcamp.appcamp16.utils;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static void closeStreamQuietly(InputStream stream) {
        try {
            if(stream != null) {
                stream.close();
            }
        } catch(IOException e) {
            // Let's ignore exception! Shall we?
        }
    }
}
