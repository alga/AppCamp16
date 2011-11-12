package lt.appcamp.appcamp16.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.util.Log;

public class CacheManager {
	
	private static final String TAG = "CacheManager";

	static File cacheDir;
	
	public static void setDir(File dir) {
		cacheDir = dir;
	}
	
	public static InputStream read(String name) {
		File file = getFile(name);
		
		if (!file.exists()) {
//			Log.v(TAG, "missed: " + name );
			return null;
		}
		
//		Log.v(TAG, "hit: " + name );
		
		try {
			return new FileInputStream(file);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}
	
	public static void write(String name, InputStream stream) {
//		Log.v(TAG, "write: " + name );
		
		try {
			OutputStream os = new FileOutputStream(getFile(name));
			
			byte[] buffer = new byte[4096];  
			int bytesRead; 
			
			while ((bytesRead = stream.read(buffer)) != -1)
				os.write(buffer, 0, bytesRead);

			os.close();

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}	
	
	private static File getFile(String name) {
		return new File(cacheDir, CryptUtils.sha1(name));		
	}
}
