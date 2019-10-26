package com.lawk.villagereach;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

public class Sync {
    private static final String TAG = "myTracker";

    public static void sync(final Context context, final SyncCallback callback) {
        final String token = InternalStorageHandler.getInstance(context).readFile(context,"tokenFile.txt");
        Log.i(TAG, "Sync is now using this token from storage" + token);
        String url = "https://demo-v3.openlmis.org/api/orders?status=TRANSFER_FAILED&status=READY_TO_PACK&status=RECEIVED&status=SHIPPED&status=IN_ROUTE";
        NetworkingTest.dataFromServerString(token, url, context, new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"Syncing");
                final Gson gson = new Gson();
                OrderResponse rez = gson.fromJson(result, OrderResponse.class);
                for (int index = 0; index < rez.content.length; index++) {
                    String podUrl = "https://demo-v3.openlmis.org/api/proofsOfDelivery?orderId=" + rez.content[index].id;
                    NetworkingTest.dataFromServerString(token, podUrl, context, new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            ShipmentResponse rez = gson.fromJson(result, ShipmentResponse.class);
                            //for each thing in rez.content, add rez.content to storage
                        }
                        @Override
                        public void onFailure(VolleyError error) {

                        }
                    });
                }
                callback.onSuccess(rez.content);
            }

            @Override
            public void onFailure(VolleyError error) {
                callback.onFailure(error);
            }
        });






//
//
//        NetworkingTest.dataFromServer(token, url, context, new VolleyCallback() {
//            @Override
//            public void onSuccess(JSONObject result) {
//                //parse result as object
//                //store into storage
////                Gson gson = new Gson();
////                try {
////                    JSONArray orderArray = result.getJSONArray("content");
////                    for (int index = 0; index < orderArray.length(); index++) {
////                        JSONObject order = orderArray.getJSONObject(index);
////                        OrderTest newOrder = gson.fromJson(order, OrderTest.class);
////                    }
////                } catch(JSONException ex) {
////
////                }
//
//
//                Log.i(TAG,"Syncing");
//                //change type to whatever is the most convinient for the UI
//                Order[] orderArray = new Order[10];
//                callback.onSuccess(orderArray);
//            }
//            @Override
//            public void onFailure(VolleyError error) {
//                callback.onFailure(error);
//            }
//        });
    }
}