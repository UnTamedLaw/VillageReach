package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

//import static com.lawk.villagereach.R.id.static_spinner_for_rejection_reason;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static final String KEY = FormActivity.class.toString();
    private static String RESULT = "DeliveryResponse";
    private RecyclerView recyclerView;
    private ProofOfDeliveryRecyclerAdaptor podRecyclerAdapter;
    private static final String TAG = "Second Activity Button";

    private ProofOfDelivery currentPod;
    private Shipment currentShipment;
    private Order currentOrder;
    private HashMap<String, Orderable> orderableHashMap;
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


//    //adding spinner for the dropdown for rejection reason
//        //two are needed, static and dynamic
//
//        Spinner staticSpinner = (Spinner) findViewById(static_spinner_for_rejection_reason);
//        // ArrayAdapter for the reasons---do we really need?
//        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
//                .createFromResource(this, R.array.rejection_reason,
//                        android.R.layout.simple_spinner_item);
//
//        // list of "items to view, simple_spinner_dropdown_item?
//        staticAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        //connecting adapter with our dropdown items
//        staticSpinner.setAdapter(staticAdapter);
//
//        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner_for_rejection_reason);
//
//        String[] items = new String[] { "Missing", "Mising2", "missing3" };
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, items);
//
//        dynamicSpinner.setAdapter(adapter);
//
//        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // empty
//            }
//        });


    }
    public void onClickRespond(View view){

        //clicking submit on a completed POD will move this particular POD to a completed folder
        //in the internal storage where it will wait for sync. and available internet connection.

        startActivity(new Intent(FormActivity.this, DeliveryActivity.class ));

    }
    //Adding First Iteration of JSON Parser for proof of delivery.
    //From demo file "api stuff," we are first capturing
    // {shipment:
    //  {order:
    //      {externalId: "17542db8-7912-4b9f-a922-0b111e1565c8", emergency: false,…},…},…}
    //deliveredBy: null
    // after we will be parsing id, lineItems
    // orderable

}