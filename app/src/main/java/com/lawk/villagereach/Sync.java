package com.lawk.villagereach;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Sync {
    private static final String TAG = "myTracker";

    public static void sync(final Context context, final SyncCallback callback) {
        final Gson gson = new Gson();
        final String token = InternalStorageHandler.getInstance(context).readFile("token");
        Log.i(TAG, "Sync is now using this token from storage" + token);
        String podArrayUrl = "https://demo-v3.openlmis.org/api/proofsOfDelivery";
        NetworkingTest.dataFromServerString(token, podArrayUrl, context, new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "syncing");
                ProofsOfDeliveryContent allPods = gson.fromJson(result, ProofsOfDeliveryContent.class);
                ProofOfDelivery[] allPodsArray = allPods.content;
                HashMap<String, Object> podMap = new HashMap<>();
                HashMap<String, Object> orderableMap = new HashMap<>();
                final HashMap<String, Object> podOrderableMap = new HashMap<>();
                //put all the proofOfDelivery items into storage by their ID
                for (int podIndex = 0; podIndex < allPodsArray.length; podIndex++) {
                    ProofOfDelivery currentPod = allPodsArray[podIndex];
                    podMap.put(currentPod.id, currentPod);
                    for (int lineItemIndex = 0; lineItemIndex < currentPod.lineItems.length; lineItemIndex++) {
                        LineItem currentLineItem = currentPod.lineItems[lineItemIndex];
                        String currentLineItemUrl = currentLineItem.orderable.href;
                        NetworkingTest.dataFromServerString(token, currentLineItemUrl, context, new StringCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Orderable orderable = gson.fromJson(result, Orderable.class);
                                //send orderable off to the storage
                                InternalStorageHandler.getInstance(context).writePodOrderableToFile(orderable);
                            }

                            @Override
                            public void onFailure(VolleyError error) {

                            }
                        });
                    }
                    String shipmentUrl = allPodsArray[podIndex].shipment.href;
//                    NetworkingTest.dataFromServerString(token, shipmentUrl, context, new StringCallback() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Shipment shipment = gson.fromJson(result, Shipment.class);
//                            //send shipment to storage
//                            String orderUrl = shipment.order.href;
//
//                            NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
//                                @Override
//                                public void onSuccess(String result) {
//                                    Order order = gson.fromJson(result, Order.class);
//                                    //send order to storage
//
//                                }
//                                @Override
//                                public void onFailure(VolleyError error) {
//
//                                }
//                            });
//                        }
//                        @Override
//                        public void onFailure(VolleyError error) {
//
//                        }
//                    });
                    String orderUrl = "https://demo-v3.openlmis.org/api/orders";
                    NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i(TAG, result);
                            OrdersContent ordersContent = gson.fromJson(result, OrdersContent.class);
                            for (int orderIndex = 0; orderIndex < ordersContent.content.length; orderIndex++) {
                                Order order = ordersContent.content[orderIndex];
                                InternalStorageHandler.getInstance(context).writeOrderToFile(order);
                            }
                        }

                        @Override
                        public void onFailure(VolleyError error) {

                        }
                    });
                }
                InternalStorageHandler.getInstance(context).writeToFile(gson.toJson(podMap), "podMap");
//                String podmapstring = InternalStorageHandler.getInstance(context).readFile("podMap");
//                Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
//                HashMap<String, Object> podmapread = gson.fromJson(podmapstring, type);
                Log.i(TAG, "done");
            }
            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }
}