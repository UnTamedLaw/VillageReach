package com.lawk.villagereach;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ProofOfDeliveryRecyclerAdaptor extends RecyclerView.Adapter<ProofOfDeliveryRecyclerAdaptor.ViewHolder>{
    private static final String TAG = "myTracker";

    private Context context;
    private ProofOfDelivery currentPod;
    private Shipment currentShipment;
    private Order currentOrder;
    private HashMap<String, Orderable> orderableHashMap;
    private Listener listener;
    private ArrayList<LineItem> podLineItemArrayList;
    private String rejectionReason;
    public HashMap<String, FormActivityLineItemEditable> formData;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int id);
    }

    public ProofOfDeliveryRecyclerAdaptor(Context context, ProofOfDelivery currentPod, Shipment currentShipment, Order currentOrder, HashMap<String, Orderable> orderableHashMap) {
        this.context = context;
        this.currentPod = currentPod;
        this.currentShipment = currentShipment;
        this.currentOrder = currentOrder;
        this.orderableHashMap = orderableHashMap;
        this.podLineItemArrayList = new ArrayList<LineItem>();
        formData = new HashMap<String, FormActivityLineItemEditable>();
        Collections.addAll(this.podLineItemArrayList, currentPod.lineItems);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    @Override
    public int getItemCount() {

        return podLineItemArrayList.size();
    }

    @Override
    public ProofOfDeliveryRecyclerAdaptor.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pod_recycler_card, parent, false);
        return  new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final  int id) {

        CardView cardView = holder.layout;

        TextView productName = cardView.findViewById(R.id.product_name);
//        TextView productUnit = cardView.findViewById(R.id.product_unit);
        TextView quantityOrdered = cardView.findViewById(R.id.quantity_ordered);
        TextView quantityShipped = cardView.findViewById(R.id.quantity_shipped);
//        TextView lotCode = cardView.findViewById(R.id.lot_code);
        EditText quantityAccepted = cardView.findViewById(R.id.quantity_accepted);
        TextView quantityReturned = cardView.findViewById(R.id.quantity_rejected);

        final Spinner reasonRejected = cardView.findViewById(R.id.rejection_reason);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.rejection_reason, android.R.layout.simple_spinner_item);
        reasonRejected.setAdapter(adapter);
        reasonRejected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.i(TAG, (String) parent.getItemAtPosition(position));
                String item = parent.getItemAtPosition(position).toString();
                rejectionReason = item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rejectionReason = "0";
            }
        });
        EditText notes = cardView.findViewById(R.id.notes);

        //get line item for this card from the arrayList (arbitrary order)
        LineItem currentPodLineItem = podLineItemArrayList.get(id);
        //get an array of lineitems from the current shipment;
        LineItem[] shipmentLineItemArray = currentShipment.lineItems;
        Orderable currentOrderable = orderableHashMap.get(currentPodLineItem.orderable.id);
        LineItem currentShipmentLineItem;
        LineItem currentOrderLineItem;
        //the purpose of this for loop is to search for a line item inside shipmentlineitems that
        //uses the same orderable id as the current podlineitem that this cards based off of.
        for (LineItem currentLineItem : shipmentLineItemArray) {
            if (currentLineItem.orderable.id.equals(currentOrderable.id)) {
                currentShipmentLineItem = currentLineItem;
                quantityShipped.setText(Integer.toString(currentShipmentLineItem.quantityShipped));
            }
        }

        LineItem[] orderLineItemArray = currentOrder.orderLineItems;
        for (LineItem currentLineItem : orderLineItemArray) {
            if (currentLineItem.orderable.id.equals(currentOrderable.id)) {
                currentOrderLineItem = currentLineItem;
                quantityOrdered.setText(Integer.toString(currentOrderLineItem.orderedQuantity));
            }

        }

        productName.setText(currentOrderable.fullProductName);
//        productUnit.setText("this is left blank intentionally")

//        quantity ordered comes from order line items
//        quantityOrdered.setText("");

//        lotCode.setText("this is left blank intentionally");
        quantityAccepted.setText(Integer.toString(currentPodLineItem.quantityAccepted));
        quantityReturned.setText(Integer.toString(currentPodLineItem.quantityRejected));

        //notes.setText(currentPodLineItem.notes);


        FormActivityLineItemEditable fields = new FormActivityLineItemEditable(currentPodLineItem.id, currentPodLineItem.quantityAccepted, currentPodLineItem.quantityRejected, currentPodLineItem.rejectionReasonId, currentPodLineItem.notes);
        fields.setRejectionReason(rejectionReason);
        formData.put(currentPodLineItem.id, fields); //not sure if this is right
        quantityAccepted.addTextChangedListener(new LineItemEditTextListener(fields, "quantityAccepted"));
        quantityReturned.addTextChangedListener(new LineItemEditTextListener(fields, "quantityRejected"));
        notes.addTextChangedListener(new LineItemEditTextListener(fields, "notes"));

        //Log.i(TAG, "formdata" + formData.keySet());


    }

    private class LineItemEditTextListener implements TextWatcher {
        private FormActivityLineItemEditable currentLineItem;
        private String field;

        public LineItemEditTextListener(FormActivityLineItemEditable currentLineItem, String field) {
            this.currentLineItem = currentLineItem;
            this.field = field;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            int MAX_DIGITS = 7;
            if (charSequence.length() > 0 && charSequence.length() < MAX_DIGITS) {
                //needs more validation. should check if quantityaccepted is greater than quantityreceived
                if (field.equals("quantityAccepted")) {
                    Log.i(TAG, "qa " + charSequence.toString());
                    currentLineItem.setQuantityAccepted(Integer.parseInt(charSequence.toString()));
                } else if (field.equals("quantityRejected")) {
                    currentLineItem.setQuantityRejected(Integer.parseInt(charSequence.toString()));
                }
            }
            if (field.equals("notes")) {
                currentLineItem.setNotes(charSequence.toString());
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}