package com.lawk.villagereach;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sync {
    private static final String TAG = "myTracker";


    public static void sync(Context context) {
        if (Networking.isConnected(context)) {
            //sendDrafts();
            requestData(context, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    Log.i(TAG,result.toString());
                    try {
                        JSONArray content = result.getJSONArray("content");
                        Log.i(TAG,"processing batch with number of orders:" + Integer.toString(content.length()));
                        for (int index = 0; index < content.length(); index++) {
                            JSONObject order = content.getJSONObject(index);
                            String id = order.getString("id");
                            //code to make order objects here
                            Log.i(TAG, "processing id: " + id);
                        }
                    } catch(Exception e) {
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }
            });
        }
    }


    private static void sendDrafts() {
        //request to send off drafts
    }

    private static void requestData(Context context, final VolleyCallback callback) {
        //make this the real token when storage is ready
        final String token = "bearer 104cc482-861e-4af9-b77b-26ff92c42e4d";
        Log.i(TAG, "requesting data");
        String url = "https://demo-v3.openlmis.org/api/orders?status=TRANSFER_FAILED&status=READY_TO_PACK&status=RECEIVED&status=SHIPPED&status=IN_ROUTE";
        JsonObjectRequest dataRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
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
                headers.put("Authorization", token);
                headers.put("content-type", "application/json; charset=utf-8");
                return headers;
            }
        };
        Networking.getInstance(context).addToRequestQueue(dataRequest);
    }
}