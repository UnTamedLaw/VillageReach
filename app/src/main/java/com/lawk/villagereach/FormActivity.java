package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    private static final String TAG = "myTracker";
    private RecyclerView recyclerView;
    private ProofOfDeliveryRecyclerAdaptor podRecyclerAdapter;
    private ProofOfDelivery currentPod;
    private Shipment currentShipment;
    private Order currentOrder;
    private HashMap<String, Orderable> orderableHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Gson gson = new Gson();
        super.onCreate(savedInstanceState);
        String podId = getIntent().getStringExtra("podId");

        String podHashMapString = InternalStorageHandler.getInstance(this).readFile("podMap");
        Type podType = new TypeToken<HashMap<String, ProofOfDelivery>>() {}.getType();
        HashMap<String, ProofOfDelivery> podHashMap = gson.fromJson(podHashMapString, podType);
        this.currentPod = podHashMap.get(podId);

        String shipmentHashmapString = InternalStorageHandler.getInstance(this).readFile("shipmentMap");
        Type shipmentType = new TypeToken<HashMap<String, Shipment>>() {}.getType();
        HashMap<String, Shipment> shipmentHashMap = gson.fromJson(shipmentHashmapString, shipmentType);
        this.currentShipment = shipmentHashMap.get(currentPod.shipment.id);

        this.currentOrder = currentShipment.order;

        String orderableHashMapString = InternalStorageHandler.getInstance(this).readFile("orderableMap");
        Type orderableType = new TypeToken<HashMap<String, Orderable>>() {}.getType();
        this.orderableHashMap = gson.fromJson(orderableHashMapString, orderableType);

        setContentView(R.layout.activity_form);
        this.recyclerView = findViewById(R.id.pod_recyclerview);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        podRecyclerAdapter = new ProofOfDeliveryRecyclerAdaptor(FormActivity.this, currentPod, currentShipment, currentOrder, orderableHashMap);
        recyclerView.setAdapter(podRecyclerAdapter);

        Button submitButton = findViewById(R.id.submit);

        if (currentPod.status.equals("CONFIRMED")) {
            submitButton.setEnabled(false);
        }

        String requestHashMapString = InternalStorageHandler.getInstance(this).readFile("requestMap");
        Type requestType = new TypeToken<HashMap<String, Orderable>>() {}.getType();
        HashMap<String, Request> requestHashMap = gson.fromJson(requestHashMapString, requestType);
        if (requestHashMap.keySet().contains(currentPod.id)) {
            submitButton.setEnabled(false);
        }
    }

    private Request generateRequest() {
        String deliveredBy = ((EditText) findViewById(R.id.delivery_signature)).getText().toString();
        String receivedBy = ((EditText) findViewById(R.id.receive_signature)).getText().toString();
        HashMap<String, FormActivityLineItemEditable> formData = podRecyclerAdapter.formData;
        Request request = new Request();
        request.id = currentPod.id;
        request.deliveredBy = deliveredBy;
        request.receivedBy = receivedBy;
        request.status = "INITIATED"; //CHANGE THIS!!!
        Stub shipmentStub = new Stub();
        shipmentStub.id = currentShipment.id;
        shipmentStub.href = "https://demo-v3.openlmis.org/api/shipments/".concat(currentShipment.id);
        shipmentStub.versionNumber = 0; //change this later
        request.shipment = shipmentStub;
        request.receivedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ArrayList<LineItem> requestLineItemArrayList = new ArrayList<LineItem>();
        for (String lineItemId : formData.keySet()) {
            FormActivityLineItemEditable currentEditable = formData.get(lineItemId);
            LineItem podLineItem = findLineItemByIdInArray(lineItemId, currentPod.lineItems);
            LineItem requestLineItem = new LineItem();
            requestLineItem.id = currentEditable.id;
            requestLineItem.orderable = podLineItem.orderable;
            requestLineItem.quantityAccepted = currentEditable.quantityAccepted;
            requestLineItem.quantityRejected = currentEditable.quantityRejected;
            requestLineItem.quantityShipped = currentEditable.quantityShipped;
            requestLineItem.notes = currentEditable.notes;
            requestLineItemArrayList.add(requestLineItem);
        }
        request.lineItems = requestLineItemArrayList.toArray(new LineItem[requestLineItemArrayList.size()]);
        return request;
    }

    public void onClickRespond(View view) {
        if (validateAllFormData()) {
            Request request = generateRequest();
            InternalStorageHandler.getInstance(this).writeRequestToFile(request);
            showCustomToast("This delivery has been saved and is ready to sync. Because it is signed, it can no longer be edited.");
            this.finish();
        }
    }

    private LineItem findLineItemByIdInArray(String id, LineItem[] array) {
        for (LineItem currentLineItem : array) {
            if (currentLineItem.id.equals(id)) {
                return currentLineItem;
            }
        }
        return null;
    }

    private boolean validateFormActivityLineItemEditableCollection(Collection<FormActivityLineItemEditable> array) {
        boolean isValid = true;
        for (FormActivityLineItemEditable editable : array) {
            if (!validateFormActivityLineItemEditable(editable)) {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean validateFormActivityLineItemEditable(FormActivityLineItemEditable editable) {
        if (editable.quantityAccepted + editable.quantityRejected == editable.quantityShipped) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateAllFormData() {
        String deliveredBy = ((EditText) findViewById(R.id.delivery_signature)).getText().toString();
        String receivedBy = ((EditText) findViewById(R.id.receive_signature)).getText().toString();
        if (deliveredBy.equals("") || receivedBy.equals("")) {
            showCustomToast("Delivery requires both signatures to be saved.");
            return false;
        }
        if(!validateFormActivityLineItemEditableCollection(podRecyclerAdapter.formData.values())){
            showCustomToast("Quantities accepted and rejected must add up to the quantity ordered.");
            return false;
        }
        return true;
    }

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}