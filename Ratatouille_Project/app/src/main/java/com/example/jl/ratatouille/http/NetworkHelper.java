package com.example.jl.ratatouille.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jav on 10/23/2017.
 */

public class NetworkHelper {
    /**
     * returns whether the there is network access
     * @param context the context of which we find the network access
     * @return boolean true if it has network access, false otherwise
     */
    public static boolean hasNetworkAccess(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
