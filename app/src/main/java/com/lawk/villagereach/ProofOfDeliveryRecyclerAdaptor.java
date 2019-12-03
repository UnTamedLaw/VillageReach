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
    private ArrayList<LineItem> podLineItemArrayList;
    private String rejectionReason;
    public HashMap<String, FormActivityLineItemEditable> formData;

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
        public CardView layout;
        public TextView productName;
        public TextView quantityOrdered;
        public TextView quantityShipped;
        public EditText quantityAccepted;
        public EditText quantityRejected;
        public Spinner reasonRejected;
        public EditText notes;
        public LineItemEditTextListener quantityAcceptedListener;
        public LineItemEditTextListener quantityRejectedListener;
        public LineItemEditTextListener notesListener;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.layout = cardView;
            this.productName = layout.findViewById(R.id.product_name);
            this.quantityOrdered = layout.findViewById(R.id.quantity_ordered);
            this.quantityShipped = layout.findViewById(R.id.quantity_shipped);
            this.quantityAccepted = layout.findViewById(R.id.quantity_accepted);
            this.quantityRejected = layout.findViewById(R.id.quantity_rejected);
            this.reasonRejected = layout.findViewById(R.id.rejection_reason);
            this.notes = layout.findViewById(R.id.notes);
        }

        public void setListeners(LineItemEditTextListener quantityAcceptedListener, LineItemEditTextListener quantityRejectedListener, LineItemEditTextListener notesListener) {
            this.quantityAcceptedListener = quantityAcceptedListener;
            quantityAccepted.addTextChangedListener(quantityAcceptedListener);
            this.quantityRejectedListener = quantityRejectedListener;
            quantityRejected.addTextChangedListener(quantityRejectedListener);
            this.notesListener = notesListener;
            notes.addTextChangedListener(notesListener);
        }

        public void setListenersData(FormActivityLineItemEditable data) {
            this.quantityAcceptedListener.setData(data);
            this.quantityRejectedListener.setData(data);
            this.notesListener.setData(data);
        }
    }

    private class LineItemEditTextListener implements TextWatcher {
        private View view;
        private FormActivityLineItemEditable data;

        private LineItemEditTextListener(View view) {
            this.view = view;
        }

        public void setData(FormActivityLineItemEditable data) {
            this.data = data;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable editable) {
            String value = editable.toString();
            if (data != null) {
                switch(view.getId()) {
                    case R.id.quantity_accepted:
                        data.quantityAccepted = Integer.parseInt(value);
                        break;
                    case R.id.quantity_rejected:
                        data.quantityRejected = Integer.parseInt(value);
                        break;
                    case R.id.notes:
                        data.notes = value;
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return podLineItemArrayList.size();
    }

    @Override
    public ProofOfDeliveryRecyclerAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //set everything in the cardView that doesn't change
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.pod_recycler_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        //this line of code looks dumb but every EditText needs it's own TextWatcher and this seems to be the cleanest way to do it.
        viewHolder.setListeners(new LineItemEditTextListener(viewHolder.quantityAccepted), new LineItemEditTextListener(viewHolder.quantityRejected), new LineItemEditTextListener(viewHolder.notes));
        viewHolder.reasonRejected.setAdapter(ArrayAdapter.createFromResource(context, R.array.rejection_reason, android.R.layout.simple_spinner_item));

        viewHolder.reasonRejected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Log.i(TAG, (String) parent.getItemAtPosition(position));

                String item = parent.getItemAtPosition(position).toString();
                rejectionReason = item;
                Log.i(TAG, rejectionReason + " " + orderableHashMap.get(id));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rejectionReason = "N/A";
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final  int id) {
        //get all the data associated with this lineItem
        LineItem currentPodLineItem = podLineItemArrayList.get(id);

        LineItem currentShipmentLineItem = findShipmentLineItemByMatchingOrderable(currentPodLineItem.orderable.id);
        LineItem currentOrderLineItem = findOrderLineItemByMatchingOrderable(currentPodLineItem.orderable.id);
        Orderable currentOrderable = orderableHashMap.get(currentPodLineItem.orderable.id);
        FormActivityLineItemEditable fields = new FormActivityLineItemEditable(currentPodLineItem.id, currentShipmentLineItem.quantityShipped, currentPodLineItem.quantityAccepted, currentPodLineItem.quantityRejected, currentPodLineItem.rejectionReasonId, currentPodLineItem.notes);
        //setListenersData will change all three TextWatchers in holder to mutate fields.
        holder.setListenersData(fields);
        formData.put(currentPodLineItem.id, fields);

        holder.productName.setText(currentOrderable.fullProductName);
        holder.quantityOrdered.setText(Integer.toString(currentOrderLineItem.orderedQuantity));
        holder.quantityShipped.setText(Integer.toString(currentShipmentLineItem.quantityShipped));
    }
    //the next two functions are needed because the only way to tell which lineItems
    //are associated with what order is to go by their orderable's ID.
    private LineItem findOrderLineItemByMatchingOrderable(String orderableId) {
        for (LineItem currentOrderLineItem : currentOrder.orderLineItems) {
            if (currentOrderLineItem.orderable.id.equals(orderableId)) {
                return currentOrderLineItem;
            }
        }
        return null;
    }
    private LineItem findShipmentLineItemByMatchingOrderable(String orderableId) {
        for  (LineItem currentShipmentLineItem : currentShipment.lineItems) {
            if (currentShipmentLineItem.orderable.id.equals(orderableId)) {
                return currentShipmentLineItem;
            }
        }
        return null;
    }
}