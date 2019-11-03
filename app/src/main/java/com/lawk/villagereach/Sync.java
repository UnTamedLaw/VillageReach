package com.lawk.villagereach;
import android.content.Context;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

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
                Log.i(TAG, "Sync: syncing begins");
                //make a map from all the pods gotten from inside the result (in content)
                final ProofsOfDeliveryContent allPods = gson.fromJson(result, ProofsOfDeliveryContent.class);
                HashMap<String, ProofOfDelivery> podMap = new HashMap<>();
                Set<String> orderableIdSet = new HashSet<>();
                for (ProofOfDelivery currentPod : allPods.content) {
                    podMap.put(currentPod.id, currentPod);
                    for (LineItem currentLineItem : currentPod.lineItems) {
                        orderableIdSet.add(currentLineItem.orderable.id);
                    }
                }
                String podMapString = gson.toJson(podMap);
                InternalStorageHandler.getInstance(context).writeToFile(podMapString, "podMap");
                Log.i(TAG, "Sync: pods written to file");
                String orderablesUrl = constructUrlWithSetIds("https://demo-v3.openlmis.org/api/orderables/","id",orderableIdSet);

                NetworkingTest.dataFromServerString(token, orderablesUrl, context, new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        OrderableContent allOrderables = gson.fromJson(result, OrderableContent.class);
                        HashMap<String, Orderable> orderableMap = new HashMap<>();
                        for (Orderable currentOrderable : allOrderables.content) {
                            orderableMap.put(currentOrderable.id, currentOrderable);
                        }
                        String orderableMapString = gson.toJson(orderableMap);
                        InternalStorageHandler.getInstance(context).writeToFile(orderableMapString, "orderableMap");
                        Log.i(TAG,"Sync: orderables written to file");
                        int numberOfRequestsToMake = allPods.content.length;
                        //countDownLatch starts with the number of requests i have left to make (basically one for every proof of delivery
                        //and goes down every time one completes or fails
                        final CountDownLatch countDownLatch = new CountDownLatch(numberOfRequestsToMake);
                        for (ProofOfDelivery currentPod : allPods.content) {
                            String podSpecificShipmentOrderUrl = currentPod.shipment.href + "?expand=order";
                            NetworkingTest.dataFromServerString(token, podSpecificShipmentOrderUrl, context, new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    Shipment currentShipment = gson.fromJson(result, Shipment.class);
                                    Order currentOrder = currentShipment.order;
                                    InternalStorageHandler.getInstance(context).writeShipmentToFile(currentShipment);
                                    Log.i(TAG, "Sync: a shipment written to file");
                                    InternalStorageHandler.getInstance(context).writeOrderToFile(currentOrder);
                                    Log.i(TAG, "Sync: a order written to file");
                                    countDownLatch.countDown();
                                    //when the countdown hits 0, execute the callback
                                    if (countDownLatch.getCount() == 0) {
                                        Log.i(TAG, "Sync: sync completed");
                                        callback.onSuccess();
                                    }
                                }
                                @Override
                                public void onFailure(VolleyError error) {
                                    countDownLatch.countDown();
                                    if (countDownLatch.getCount() == 0) {
                                        Log.i(TAG, "Sync: sync maybe failed");
                                        callback.onFailure(error);
                                    }
                                }
                            });
                        }
                    }
                    @Override
                    public void onFailure(VolleyError error) {
                    }
                });

            }
            @Override
            public void onFailure(VolleyError error) {
            }
        });
    }

    public static String constructUrlWithSetIds(String url, String param, Set<String> idSet) {
        String newUrl = url;
        Boolean first = true;
        for (String id : idSet) {
            if (first == true) {
                first = false;
                newUrl += "?" + param + "=";
                newUrl += id;
            }
            newUrl += "&" + param + "=";
            newUrl += id;
        }
        return newUrl;
    }
}