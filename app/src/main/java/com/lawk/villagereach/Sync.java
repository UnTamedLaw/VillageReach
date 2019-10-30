package com.lawk.villagereach;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

public class Sync {
    private static final String TAG = "myTracker";

    public static void sync(final Context context, final SyncCallback callback) {
        final Gson gson = new Gson();
        final String token = InternalStorageHandler.getInstance(context).readFile(context,"tokenFile.txt");
        Log.i(TAG, "Sync is now using this token from storage" + token);
        //String url = "https://demo-v3.openlmis.org/api/orders?status=TRANSFER_FAILED&status=READY_TO_PACK&status=RECEIVED&status=SHIPPED&status=IN_ROUTE";
        String podArrayUrl = "https://demo-v3.openlmis.org/api/proofsOfDelivery";
        NetworkingTest.dataFromServerString(token, podArrayUrl, context, new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "syncing");
                Log.i(TAG, result);
                ProofsOfDeliveryContent allPods = gson.fromJson(result, ProofsOfDeliveryContent.class);
                ProofOfDelivery[] allPodsArray = allPods.content;
                //put all the proofOfDelivery items into storage by their ID
                for (int podIndex = 0; podIndex < allPodsArray.length; podIndex++) {
                    ProofOfDelivery currentPod = allPodsArray[podIndex];
                    for (int lineItemIndex = 0; lineItemIndex < currentPod.lineItems.length; lineItemIndex++) {
                        LineItem currentLineItem = currentPod.lineItems[lineItemIndex];
                        String currentLineItemUrl = currentLineItem.orderable.href;
                        NetworkingTest.dataFromServerString(token, currentLineItemUrl, context, new StringCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Orderable orderable = gson.fromJson(result, Orderable.class);
                                //send orderable off to the storage
                                Log.i(TAG, result);

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
                            Shipment shipment = gson.fromJson(result, Shipment.class);
                            //send shipment to storage
                            String orderUrl = shipment.order.href;

                            NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    Order order = gson.fromJson(result, Order.class);
                                    //send order to storage

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
//                    String orderUrl = "https://demo-v3.openlmis.org/api/order/"
//                    NetworkingTest.dataFromServerString(token, orderUrl, context, new StringCallback() {
//                        @Override
//                        public void onSuccess(String result) {
//
//                        }
//
//                        @Override
//                        public void onFailure(VolleyError error) {
//
//                        }
//                    });
                }

            }
            @Override
            public void onFailure(VolleyError error) {

            }
        });
//        NetworkingTest.dataFromServerString(token, url, context, new StringCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i(TAG,"Syncing");
//                final Gson gson = new Gson();
//                OrderResponse rez = gson.fromJson(result, OrderResponse.class);
//                for (int index = 0; index < rez.content.length; index++) {
//                    String podUrl = "https://demo-v3.openlmis.org/api/proofsOfDelivery?orderId=" + rez.content[index].id;
//                    NetworkingTest.dataFromServerString(token, podUrl, context, new StringCallback() {
//                        @Override
//                        public void onSuccess(String result) {
//                            ShipmentResponse rez = gson.fromJson(result, ShipmentResponse.class);
//                            //for each thing in rez.content, add rez.content to storage
//                        }
//                        @Override
//                        public void onFailure(VolleyError error) {
//
//                        }
//                    });
//                }
//                callback.onSuccess(rez.content);
//            }
//
//            @Override
//            public void onFailure(VolleyError error) {
//                callback.onFailure(error);
//            }
//        });






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