package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static final String KEY = FormActivity.class.toString();
    private static String RESULT = "DeliveryResponse";
    private RecyclerView recyclerView;
    private ProofOfDeliveryRecyclerAdaptor podRecyclerAdapter;
    private static final String TAG = "SubmitTracker";

    private ProofOfDelivery currentPod;
    private Shipment currentShipment;
    private Order currentOrder;
    private HashMap<String, Orderable> orderableHashMap;
    private HashMap<String, FormActivityLineItemEditable> formData;

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


        podRecyclerAdapter = new ProofOfDeliveryRecyclerAdaptor(FormActivity.this, currentPod, currentShipment, currentOrder, orderableHashMap, formData);
        recyclerView.setAdapter(podRecyclerAdapter);

        Button submitButton = findViewById(R.id.submit);
        Button saveFormButton = findViewById(R.id.saveForm);
        if (currentPod.status.equals("CONFIRMED")) {
            submitButton.setEnabled(false);
            saveFormButton.setEnabled(false);
        }
    }


    public void onClickRespond(View view){

        //clicking submit on a completed POD will move this particular POD to a completed folder
        //in the internal storage where it will wait for sync. and available internet connection.

        HashMap<String, FormActivityLineItemEditable> formData = podRecyclerAdapter.formData;
        //Set keys = formData.keySet();

        Log.i(TAG, "quantityAccepted" + ": " + formData.get("quantityAccepted"));
        startActivity(new Intent(FormActivity.this, DeliveryActivity.class ));


    }
}