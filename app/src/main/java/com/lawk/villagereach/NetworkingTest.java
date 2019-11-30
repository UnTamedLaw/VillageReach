package com.lawk.villagereach;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkingTest {
    private static final String TAG = "myTracker";
    private static NetworkingTest instance;
    private static Context context;
    private RequestQueue requestQueue;

    private NetworkingTest(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this.context);
        }
        return this.requestQueue;
    }

    public static synchronized NetworkingTest getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkingTest(context);
        }
        return instance;
    }

    private <T> void addToRequestQueue(Request<T> req) {
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

    public static void tokenFromServer(Credentials creds, Context context, final VolleyCallback callback) {
        String url = " https://demo-v3.openlmis.org/api/oauth/token?grant_type=password&username=" + creds.username + "&password=" + creds.password;
        JsonObjectRequest tokenRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "NetworkingTest: tokenFromServer response: " + response.toString());
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
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
        NetworkingTest.getInstance(context).addToRequestQueue(tokenRequest);
    }

    public static void dataFromServerString(final String token, String url, Context context, final StringCallback callback) {
        StringRequest dataRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //stuff
                //Log.i(TAG, "NetworkingTest: dataFromServerString response: " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                headers.put("content-type", "application/json; charset=utf-8");
                return headers;
            }
        };
        NetworkingTest.getInstance(context).addToRequestQueue(dataRequest);
    }
    public static void putRequest(final String token, String url, com.lawk.villagereach.Request value, Context context, final StringCallback callback) {
        Gson gson = new Gson();
        final String json = gson.toJson(value);
        StringRequest dataRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
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
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }
        };
        NetworkingTest.getInstance(context).addToRequestQueue(dataRequest);
    }
}
