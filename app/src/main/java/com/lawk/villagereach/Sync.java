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
                Log.i(TAG, "Sync: syncing begins");
                //make a map from all the pods gotten from inside the result (in content)
                ProofsOfDeliveryContent allPods = gson.fromJson(result, ProofsOfDeliveryContent.class);
                ProofOfDelivery[] allPodsArray = allPods.content;
                HashMap<String, Object> podMap = new HashMap<>();
                //for each pod
                for (int podIndex = 0; podIndex < allPodsArray.length; podIndex++) {
                    ProofOfDelivery currentPod = allPodsArray[podIndex];
                    //add this pod to podMap
                    podMap.put(currentPod.id, currentPod);
                    //for each lineitem in the pod
                    for (int lineItemIndex = 0; lineItemIndex < currentPod.lineItems.length; lineItemIndex++) {
                        LineItem currentLineItem = currentPod.lineItems[lineItemIndex];
                        String currentLineItemUrl = currentLineItem.orderable.href;
                        InternalStorageHandler.getInstance(context).writePodLineItemToFile(currentLineItem);
                        //do a req for that pod lineitems orderable
                        NetworkingTest.dataFromServerString(token, currentLineItemUrl, context, new StringCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Orderable orderable = gson.fromJson(result, Orderable.class);
                                //send orderable off to the storage
                                InternalStorageHandler.getInstance(context).writePodOrderableToFile(orderable);
                                Log.i(TAG, "Sync: wrote a orderable to file");
                            }

                            @Override
                            public void onFailure(VolleyError error) {

                            }
                        });
                    }

                    String shipmentUrl = allPodsArray[podIndex].shipment.href;
                    NetworkingTest.dataFromServerString(token, shipmentUrl, context, new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i(TAG, "Sync: handling a shipment");
                            Shipment shipment = gson.fromJson(result, Shipment.class);
                            InternalStorageHandler.getInstance(context).writeShipmentToFile(shipment);
                            Log.i(TAG, "Sync: wrote a shipment to file");
                            //get order associated with this shipment
                            String orderUrl = shipment.order.href;
                            NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.i(TAG,"Sync: handling order associated with this shipment");
                                    Order order = gson.fromJson(result, Order.class);
                                    InternalStorageHandler.getInstance(context).writeOrderToFile(order);
                                    Log.i(TAG,"Sync: wrote order to file");
                                }
                                @Override
                                public void onFailure(VolleyError error) {
                                }
                            });
                            //write the shipment line items to files
                            for (int shipmentIndex = 0; shipmentIndex < shipment.lineItems.length; shipmentIndex++) {
                                LineItem shipmentLineItem = shipment.lineItems[shipmentIndex];
                                InternalStorageHandler.getInstance(context).writeShipmentLineItemToFile(shipmentLineItem);
                            }
                            Log.i(TAG,"Sync: wrote a batch of shipmentlineitems to file");
                            Log.i(TAG, "Sync: completed handling a shipment");
                        }
                        @Override
                        public void onFailure(VolleyError error) {

                        }
                    });
                    //get all orders
//                    String orderUrl = "https://demo-v3.openlmis.org/api/orders/" + currentPod.shipment.;
//                    NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Log.i(TAG, "Sync: handling a order");
//                            OrdersContent ordersContent = gson.fromJson(result, OrdersContent.class);
//                            for (int orderIndex = 0; orderIndex < ordersContent.content.length; orderIndex++) {
//                                Order order = ordersContent.content[orderIndex];
//                                InternalStorageHandler.getInstance(context).writeOrderToFile(order);
//                            }
//                            Log.i(TAG, "Sync: completed handling a order");
//                        }
//
//                        @Override
//                        public void onFailure(VolleyError error) {
//
//                        }
//                    });
                }
                InternalStorageHandler.getInstance(context).writeToFile(gson.toJson(podMap), "podMap");
                Log.i(TAG, "Sync: wrote all pods to file");
                Log.i(TAG, "Sync: syncing ends");
            }
            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }
}