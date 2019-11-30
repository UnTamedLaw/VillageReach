package com.lawk.villagereach;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;


public class Login {

    private static final String TAG = "myTracker";

    public static void login(String username, String password, final Context context, final AuthCallback callback) {
        Credentials creds = new Credentials(username, password);
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
                            callback.onFailure(error);
                        }
                    });
                }
                @Override
                public void onFailure(Exception error) {
                    Log.i(TAG, "Login: an unspecified error occurred");
                    callback.onFailure(error);
                }
            });
        } else {
            Log.i(TAG,"Login: using offlineLogin because no internet");
            if (offlineLogin(creds)) {
                Log.i(TAG, "Login: credentials are valid");
                callback.onSuccess();

            } else {
                Log.i(TAG, "Login: credentials are invalid");
                callback.onFailure(new Exception("offLineLoginFail"));
            }
        }
    }

    public static boolean offlineLogin(Credentials creds) {
        String credsString = InternalStorageHandler.getInstance(null).readFile("loginCredentials");
        Gson gson = new Gson();
        Credentials oldCreds = gson.fromJson(credsString, Credentials.class);
        if (creds.username.equals(oldCreds.username) && creds.password.equals(oldCreds.password)) {
            return true;
        }
        return false;
    }
}
