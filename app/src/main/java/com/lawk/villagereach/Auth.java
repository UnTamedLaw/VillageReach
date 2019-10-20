package com.lawk.villagereach;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Auth {
    private static final String TAG = "myTracker";

    public static void authenticate(Credentials creds, Context context) {
        //test code
        String authToken = requestToken(creds, context);
        //test code
        if (creds == null) {
            //if storage has creds
                //creds = storage's creds
            //else
                //throw error
        }
        //InternalStorageHandler.setToken(this.requestToken(creds))
    }


    private static String requestToken(Credentials creds, Context context) {
        //volley stuff to get string
        if (Networking.isConnected(context)) {
            requestToken(creds, context, new VolleyCallback(){
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        String token = "bearer " + result.getString("access_token");
                        Log.i(TAG, token);
                        //function that tells internal storage handler to insert the string into the db
                        //then code that tells the UI that auth has been done goes here (probably another callback)
                    } catch(JSONException error) {
                        Log.e(TAG, error.toString());
                    }
                }
            });
        }
    }

    private static void requestToken(Credentials creds, Context context, final VolleyCallback callback) {
        Log.i(TAG,"requesting token");
        String url = " https://demo-v3.openlmis.org/api/oauth/token?grant_type=password&username=" + creds.username + "&password=" + creds.password;
        JsonObjectRequest tokenRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "response code: " + Integer.toString(error.networkResponse.statusCode));
                VolleyLog.e(TAG, error);
                //throw some kind of error
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Basic dXNlci1jbGllbnQ6Y2hhbmdlbWU=");
                headers.put("content-type", "application/json; charset=utf-8");
                return headers;
            }
        };
        Networking.getInstance(context).addToRequestQueue(tokenRequest);
    }
}
