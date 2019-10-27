package com.lawk.villagereach;


import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Base64;
import android.os.Bundle;
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

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Auth {
    private static final String TAG = "myTracker";
    String AES = "AES";


    public static void authenticate(Credentials creds,final Context context, final AuthCallback callback) {
        NetworkingTest.tokenFromServer(creds, context, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    String access_token = result.getString("access_token");
                    String token = "bearer " + access_token;
                    Log.i(TAG, "Auth: now pushing this token to storage:" + token);
                    InternalStorageHandler.getInstance(context).writeToFile(token, context);
                    callback.onSuccess();
                } catch (JSONException e) {
                    Log.e(TAG, "Auth: I can't parse this Json");
                    //if this fails, there's something wrong with the server.
                }
            }

            @Override
            public void onFailure(VolleyError error) {
                Log.i(TAG, "Auth: response code: " + Integer.toString(error.networkResponse.statusCode));
                callback.onFailure(error);
            }
            });
        }



    public String encrypt (String Data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }
    public String decrypt(String outputString, String password)throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

    }
    private SecretKeySpec generateKey (String password)throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secreteKeySpec = new SecretKeySpec(key, "AES");
        return secreteKeySpec;
    }

}