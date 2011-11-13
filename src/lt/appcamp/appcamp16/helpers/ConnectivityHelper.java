package lt.appcamp.appcamp16.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityHelper {
		
	public static void requireNetwork(Context context) {
        if (!ConnectivityHelper.isNetworkConnected(context)) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(context);
        	builder.setMessage("You need to enable the network connection to use this application")
        	       .setCancelable(false)
        	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   Context context = ((Dialog) dialog).getContext();
        	        	   context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        	           }
        	       })
        	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                dialog.cancel();
        	           }
        	       });
        	
        	builder.create().show();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        return isWiFiNetworkConnected(context) || isMobileNetworkConnected(context);
    }
    
    public static boolean isWiFiNetworkConnected(Context context) {
    	return getWiFiNetworkInfo(context).isConnected();
    }

    private static NetworkInfo getWiFiNetworkInfo(Context context) {
        return getConnectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }
    
    public static boolean isMobileNetworkConnected(Context context) {
    	return getMobileNetworkInfo(context).isConnected();
    }

    private static NetworkInfo getMobileNetworkInfo(Context context) {
        return getConnectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}