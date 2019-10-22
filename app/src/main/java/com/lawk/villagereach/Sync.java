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

    public static void sync(Context context, final SyncCallback callback) {
        String token = InternalStorageHandler.getInstance(context).readFile(context,"tokenFile.txt");
        Log.i(TAG, "Sync is now using this token from storage" + token);
        String url = "https://demo-v3.openlmis.org/api/orders?status=TRANSFER_FAILED&status=READY_TO_PACK&status=RECEIVED&status=SHIPPED&status=IN_ROUTE";
        NetworkingTest.dataFromServer(token, url, context, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                //parse result as object
                //store into storage
                Log.i(TAG,"Syncing");
                //change type to whatever is the most convinient for the UI
                Order[] orderArray = new Order[10];
                callback.onSuccess(orderArray);
            }
            @Override
            public void onFailure(VolleyError error) {
                callback.onFailure(error);
            }
        });
    }
}