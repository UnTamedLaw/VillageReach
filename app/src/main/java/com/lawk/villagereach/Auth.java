package com.lawk.villagereach;


import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Auth {
    private static final String TAG = "myTracker";

    public static void authenticate(final Credentials creds,final Context context, final AuthCallback callback) {
        NetworkingTest.tokenFromServer(creds, context, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    saveCredsToStorage(creds);
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

    public static void saveCredsToStorage(Credentials creds) {
        Gson gson = new Gson();
        InternalStorageHandler.getInstance(null).writeToFile(gson.toJson(creds), "loginCredentials");
    }
}
