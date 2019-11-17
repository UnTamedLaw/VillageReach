package com.lawk.villagereach;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

public class Submit {
    private static final String TAG= "myTracker";

    public void submit( Context context, Request request){
        InternalStorageHandler.getInstance(context).writeRequestToFile(request);

    }
}