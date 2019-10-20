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
    public Auth() {
        Log.i(TAG,"auth object created");
        Credentials fakeCreds = new Credentials("administrator", "password");
    }

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
            Log.i(TAG,"requesting token");
            RequestQueue queue = Networking.getInstance(context).getRequestQueue();
            String url = " https://demo-v3.openlmis.org/api/oauth/token?grant_type=password&username=" + creds.username + "&password=" + creds.password;
            String token = "bearer ";

            JsonObjectRequest tokenRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, response.toString());
                    try {
                        String access_token = response.getString("access_token");
                        Log.i(TAG, access_token);
                    } catch (JSONException error) {
                        Log.e(TAG,error.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, "response code: " + Integer.toString(error.networkResponse.statusCode));
                    VolleyLog.e(TAG, error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic dXNlci1jbGllbnQ6Y2hhbmdlbWU=");
                    headers.put("content-type", "application/json; charset=utf-8");
                    return headers;
                }
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("grant_type", "password");
                    params.put("username", "administrator");
                    params.put("password", "password");
                    return params;
                }
            };
            //queue.add(tokenRequest);
            Networking.getInstance(context).addToRequestQueue(tokenRequest);
            return token;
        } else {
            return null;
        }

    }
}
