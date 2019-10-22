package com.lawk.villagereach;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Networking {
    private static final String TAG = "myTracker";
    private static Networking instance;
    private RequestQueue requestQueue;
    private static Context context;

    private Networking(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this.context);
        }
        return this.requestQueue;
    }

    public static synchronized Networking getInstance(Context context) {
        if (instance == null) {
            instance = new Networking(context);
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            Log.i(TAG,"Connected to internet");
            return true;
        } else {
            Log.i(TAG,"Not connected to internet");
            return false;
        }
    }
}
