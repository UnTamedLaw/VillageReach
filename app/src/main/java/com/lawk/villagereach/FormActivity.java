package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static final String KEY = FormActivity.class.toString();
    private static String RESULT = "DeliveryResponse";
    private RecyclerView recyclerView;
    private ProofOfDeliveryRecyclerAdaptor podRecyclerAdapter;
    private static final String TAG = "myTracker";
    private ProofOfDelivery currentPod;
    private Shipment currentShipment;
    private Order currentOrder;
    private HashMap<String, Orderable> orderableHashMap;
    private String Jsonstring;
    private String quantityAccepted;
    private String rejectionReason;
    public String myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String podId = getIntent().getStringExtra("podId");

        Gson gson = new Gson();
        String podHashMapString = InternalStorageHandler.getInstance(this).readFile("podMap");
        Type podType = new TypeToken<HashMap<String, ProofOfDelivery>>(){}.getType();
        HashMap<String, ProofOfDelivery> podHashMap = gson.fromJson(podHashMapString, podType);
        currentPod = podHashMap.get(podId);

        String shipmentHashmapString = InternalStorageHandler.getInstance(this).readFile("shipmentMap");
        Type shipmentType = new TypeToken<HashMap<String, Shipment>>(){}.getType();
        HashMap<String, Shipment> shipmentHashMap = gson.fromJson(shipmentHashmapString, shipmentType);
        currentShipment = shipmentHashMap.get(currentPod.shipment.id);

        currentOrder = currentShipment.order;

        String orderableHashMapString = InternalStorageHandler.getInstance(this).readFile("orderableMap");
        Type orderableType = new TypeToken<HashMap<String, Orderable>>(){}.getType();
        orderableHashMap = gson.fromJson(orderableHashMapString, orderableType);

        setContentView(R.layout.activity_form);
        recyclerView = findViewById(R.id.pod_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        podRecyclerAdapter = new ProofOfDeliveryRecyclerAdaptor(FormActivity.this, currentPod, currentShipment, currentOrder, orderableHashMap);
        recyclerView.setAdapter(podRecyclerAdapter);

        Button submitButton = findViewById(R.id.submit);
        Button saveFormButton = findViewById(R.id.saveForm);
        if (currentPod.status.equals("CONFIRMED")) {
            submitButton.setEnabled(false);
            saveFormButton.setEnabled(false);
        }




    public void onClickRespond(View view){

//      clicking submit on a completed POD will move this particular POD to a completed folder
//      in the internal storage where it will wait for sync. and available internet connection.
        EditText deliverer = findViewById(R.id.delivery_signature);
        EditText receiver = findViewById(R.id.receive_signature);
        String deliveredBy = deliverer.getText().toString();
        String receivedBy = receiver.getText().toString();
        //Log.i(TAG, deliveredBy +" "+ receivedBy);

        HashMap<String, FormActivityLineItemEditable> formData = podRecyclerAdapter.formData;
        //Log.i(TAG, "foo " + podRecyclerAdapter.formData.get(rejectionReason));
        //Log.i(TAG, "formdata" + ": " + podRecyclerAdapter.formData.keySet());

        int size = podRecyclerAdapter.getItemCount();
        Log.i(TAG, "size " + String.valueOf(size));

        myData = "";
        Log.i(TAG, "1" + myData);

        JSONObject draft = new JSONObject();
        try {
            draft.put("deliveredBy", deliveredBy);
            draft.put("id", currentOrder.id);
            draft.put("receivedBy", receivedBy);
            String currentDate = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date());
            draft.put("receivedDate", currentDate);
            JSONObject jsonShipment = new JSONObject();
            jsonShipment.put("href", "https://demo-v3.openlmis.org/api/shipments/".concat(currentShipment.id));
            jsonShipment.put("id", currentShipment.id);
            jsonShipment.put("versionNumber", 0); //dummy
            draft.put("shipment", jsonShipment);
            draft.put("status", "CONFIRMED");
            JSONArray jsonLineItems = new JSONArray();
            for (FormActivityLineItemEditable currentEditable : formData.values()) {
                LineItem oldLineItem;
                for (LineItem currentOldLineItem: currentPod.lineItems) {
                    if (currentOldLineItem.id.equals(currentEditable.id)) {
                        oldLineItem = currentOldLineItem;
                        JSONObject currentJsonLineItem = new JSONObject();
                        currentJsonLineItem.put("id", oldLineItem.id);
                        JSONObject jsonOrderable = new JSONObject();
                        jsonOrderable.put("id", oldLineItem.orderable.id);
                        jsonOrderable.put("href", oldLineItem.orderable.href);
                        jsonOrderable.put("versionNumber", oldLineItem.orderable.versionNumber);
                        currentJsonLineItem.put("orderable", jsonOrderable);
                        currentJsonLineItem.put("quantityAccepted", currentEditable.quantityAccepted);
                        currentJsonLineItem.put("quantityRejected", currentEditable.quantityRejected);
                        currentJsonLineItem.put("quantityShipped", oldLineItem.quantityShipped);
                        currentJsonLineItem.put("notes", currentEditable.notes);
                        jsonLineItems.put(currentJsonLineItem);
                    }
                }
            }
            draft.put("lineItems", jsonLineItems);
            Log.i(TAG, draft.toString());
        } catch (JSONException e) {
            Log.e(TAG, "JSON error when parsing form");
        }






//        try {
//            json.put("deliveredBy", deliveredBy);
//            json.put("id", currentOrder.id);
//
//            JSONArray lineItems = new JSONArray();
//            for (int i = 0; i<size; i++ ){
//                JSONObject item = new JSONObject();
//                item.put("id", "test");
//
//                JSONObject orderable = new JSONObject();
//                orderable.put("href", "https://demo-v3.openlmis.org/api/orderables/23819693-0670-4c4b-b400-28e009b86b51");
////                String s = quantityAccepted;
////                Log.i(TAG, s);
//
//                orderable.put("id", 1 );
//                orderable.put("versionNumber", 1);
//                item.put("orderable", orderable);
//
//                item.put("quantityAccepted", 0);
//                item.put("quantityRejected",0);
//                item.put("quantityShipped",0);
//
//                lineItems.put(item);
//                json.put("lineItems", lineItems);
//            }
//
//            json.put("receivedBy", receivedBy);
//            String currentDate = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date());
//            json.put("receivedDate", currentDate);
//
//            JSONObject shipment = new JSONObject();
//            shipment.put("href", "https://demo-v3.openlmis.org/api/shipments/".concat(currentShipment.id));
//            shipment.put("id", currentShipment.id);
//            shipment.put("versionNumber", 0);
//            json.put("shipment", shipment);
//
//            json.put("status", "CONFIRMED");
//            myData = json.toString();
//        }
//        catch (JSONException e) {
//            Log.i(TAG, "JSON error");
//        }
//        Log.i(TAG, myData);
//        startActivity(new Intent(FormActivity.this, DeliveryActivity.class ));
    }
}