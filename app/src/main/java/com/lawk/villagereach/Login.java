package com.lawk.villagereach;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class Login {

    private static final String TAG = "myTracker";

    public static void login(String username, String password, final Context context, final AuthCallback callback) {
        Credentials creds = new Credentials(username, password);
        final String test = "test";
        if (NetworkingTest.isConnected(context)) {
            Auth.authenticate(creds, context, new AuthCallback() {
                @Override
                public void onSuccess() {
                    Sync.sync(context, new SyncCallback() {
                        @Override
                        public void onSuccess() {
                            Log.i(TAG,"Login: authorization & syncing completed.");
                            callback.onSuccess();
                        }
                        @Override
                        public void onFailure(Exception error) {
                            //passing some unspecified error thru callback
                            callback.onFailure(error);
                        }
                    });
                }
                @Override
                public void onFailure(Exception error) {
                    Log.i(TAG, "Login: an unspecified error occurred");
                    if (error instanceof AuthFailureError) {
                        //rejected by server so passing an authfailure thru callback
                        callback.onFailure(new AuthFailureError());
                    }
                }
            });
        } else {
            Log.i(TAG,"Login: using offlineLogin because no internet");
            if (offlineLogin(creds)) {
                //pretend user logged in
                callback.onSuccess();
            } else {
                //doesn't match so passing an authfailure thru callback
                callback.onFailure(new AuthFailureError());
            }
        }
    }

    public static boolean offlineLogin(Credentials creds) {
        //get correct creds from storage
        //compare
        //return true if same
        return true;
    }
}
