package com.lawk.villagereach;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
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

    public static void authenticate(Credentials creds,final Context context, final AuthCallback callback) {
        NetworkingTest.tokenFromServer(creds, context, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    String access_token = result.getString("access_token");
                    String token = "bearer " + access_token;
                    Log.i(TAG,"Auth: now pushing this token to storage:" + token);
                    InternalStorageHandler.getInstance(context).writeToFile(token, "token");
                    callback.onSuccess();
                } catch(JSONException e) {
                    Log.e(TAG,"Auth: I can't parse this Json");
                    //if this fails, there's something wrong with the server.
                }
            }
            @Override
            public void onFailure(VolleyError error) {
//                Log.i(TAG, "Auth: response code: " + Integer.toString(error.networkResponse.statusCode));
                callback.onFailure(error);
            }
        });

    }
}
