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
import java.util.ArrayList;
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
        Type podType = new TypeToken<HashMap<String, ProofOfDelivery>>() {
        }.getType();
        HashMap<String, ProofOfDelivery> podHashMap = gson.fromJson(podHashMapString, podType);
        currentPod = podHashMap.get(podId);

        String shipmentHashmapString = InternalStorageHandler.getInstance(this).readFile("shipmentMap");
        Type shipmentType = new TypeToken<HashMap<String, Shipment>>() {
        }.getType();
        HashMap<String, Shipment> shipmentHashMap = gson.fromJson(shipmentHashmapString, shipmentType);
        currentShipment = shipmentHashMap.get(currentPod.shipment.id);

        currentOrder = currentShipment.order;

        String orderableHashMapString = InternalStorageHandler.getInstance(this).readFile("orderableMap");
        Type orderableType = new TypeToken<HashMap<String, Orderable>>() {
        }.getType();
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

        Request request = new Request();
        request.id = currentPod.id;
        request.deliveredBy = deliveredBy;
        request.receivedBy = receivedBy;
        ArrayList<LineItem> lineItemArrayList = new ArrayList<LineItem>();

        for (FormActivityLineItemEditable currentEditable : formData.values()) {
            LineItem oldLineItem;
            for (LineItem currentOldLineItem: currentPod.lineItems) {
                if (currentOldLineItem.id.equals(currentEditable.id)) {
                    oldLineItem = currentOldLineItem;
//                    JSONObject currentJsonLineItem = new JSONObject();
//                    currentJsonLineItem.put("id", oldLineItem.id);
//                    JSONObject jsonOrderable = new JSONObject();
//                    jsonOrderable.put("id", oldLineItem.orderable.id);
//                    jsonOrderable.put("href", oldLineItem.orderable.href);
//                    jsonOrderable.put("versionNumber", oldLineItem.orderable.versionNumber);
//                    currentJsonLineItem.put("orderable", jsonOrderable);
//                    currentJsonLineItem.put("quantityAccepted", currentEditable.quantityAccepted);
//                    currentJsonLineItem.put("quantityRejected", currentEditable.quantityRejected);
//                    currentJsonLineItem.put("quantityShipped", oldLineItem.quantityShipped);
//                    currentJsonLineItem.put("notes", currentEditable.notes);
                    LineItem currentLineItem = new LineItem();
                    currentLineItem.id = oldLineItem.id;
                    currentLineItem.orderable = oldLineItem.orderable;
                    currentLineItem.quantityAccepted = currentEditable.quantityAccepted;
                    currentLineItem.quantityRejected = currentEditable.quantityRejected;
                    currentLineItem.quantityShipped = oldLineItem.quantityShipped;
                    currentLineItem.notes = currentEditable.notes;
                    lineItemArrayList.add(currentLineItem);
                }
            }
        }
        LineItem[] lineItemArray = lineItemArrayList.toArray(new LineItem[lineItemArrayList.size()]);
        request.lineItems = lineItemArray;
        request.status = "CONFIRMED";
        Stub shipmentStub = new Stub();
        shipmentStub.id = currentShipment.id;
        shipmentStub.href = "https://demo-v3.openlmis.org/api/shipments/".concat(currentShipment.id);
        shipmentStub.versionNumber = 0; //dummy
        request.shipment = shipmentStub;
        String currentDate = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault()).format(new Date());
        request.receivedDate = currentDate;
        InternalStorageHandler.getInstance(this).writeRequestToFile(request);
        this.finish();

    }
}