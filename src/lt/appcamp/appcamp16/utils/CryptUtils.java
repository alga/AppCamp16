package lt.appcamp.appcamp16.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class CryptUtils {
	
	private static final String TAG = "CryptUtils";

	public static String sha1(String data) {
		try
		{
			byte[] b = data.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
			algorithm.reset();
			algorithm.update(b);
			byte messageDigest[] = algorithm.digest();
			StringBuilder result = new StringBuilder();
			//NOTE: for some reason DigestUtils doesn't produce correct value
			//so we deal with it ourselves
			for (int i=0; i < messageDigest.length; i++) {
				result.append(Integer.toString(( messageDigest[i] & 0xff ) + 0x100, 16).substring(1));
			}
			return result.toString();
		} catch(NoSuchAlgorithmException e) {
			Log.e(TAG, "SHA1 is not a supported algorithm");
		}
		
		return null;
	}
}
