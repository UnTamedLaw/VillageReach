package com.lawk.villagereach;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {
    private static final String TAG = "Anu";
    private Context context;
    private ArrayList<Order> orderArrayList;
    private ArrayList<ProofOfDelivery> podArrayList;
    private HashMap<String, Order> orderHashMap;
    private HashMap<String, ProofOfDelivery> podHashMap;
    private HashMap<String, Shipment> shipmentHashMap;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(String id);
    }



    public OrderRecyclerAdapter(Context context) {
        this.context = context;


        String orderHashMapString = InternalStorageHandler.getInstance(context).readFile("orderMap");
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Order>>(){}.getType();
        orderHashMap = gson.fromJson(orderHashMapString, type);

        String podHashMapString = InternalStorageHandler.getInstance(context).readFile("podMap");
        Type podType = new TypeToken<HashMap<String, ProofOfDelivery>>(){}.getType();
        podHashMap = gson.fromJson(podHashMapString, podType);

        String shipmentHashMapString = InternalStorageHandler.getInstance(context).readFile("shipmentMap");
        Type shipmentType = new TypeToken<HashMap<String, Shipment>>(){}.getType();
        shipmentHashMap = gson.fromJson(shipmentHashMapString, shipmentType);

        this.orderHashMap = orderHashMap;
        this.orderArrayList = new ArrayList<Order>(orderHashMap.values());
        this.podHashMap = podHashMap;
        this.podArrayList = new ArrayList<ProofOfDelivery>(podHashMap.values());
        this.shipmentHashMap = shipmentHashMap;
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
        return orderArrayList.size();
    }

    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_recycler_card, parent, false);
        return  new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int id) {
        CardView cardView = holder.layout;
        TextView orderNumber = cardView.findViewById(R.id.order_code);
        TextView orderStatus = cardView.findViewById(R.id.status_label);
        TextView requestingFacility = cardView.findViewById(R.id.requesting_facility);
        TextView supplyingDepot = cardView.findViewById(R.id.supplying_depot);
        TextView program = cardView.findViewById(R.id.program);
        TextView period = cardView.findViewById(R.id.period);
        TextView orderDate = cardView.findViewById(R.id.order_date);
        TextView emergency = cardView.findViewById(R.id.emergency);
        Button formClick = cardView.findViewById(R.id.formClick);

        final ProofOfDelivery currentPod = podArrayList.get(id);
        final Shipment currentShipment = shipmentHashMap.get(currentPod.shipment.id);

        final Order currentOrder = currentShipment.order;

        orderNumber.setText(currentOrder.orderCode);
        orderStatus.setText(currentOrder.status);
        requestingFacility.setText(currentOrder.requestingFacility.name);
        supplyingDepot.setText(currentOrder.supplyingFacility.name);
        if (currentOrder.program != null) {
            program.setText(currentOrder.program.name);
        }
        if (currentOrder.processingPeriod != null) {
            period.setText(currentOrder.processingPeriod.name);
        }
        orderDate.setText(currentOrder.createdDate);
        emergency.setText(currentOrder.emergency.toString());

        formClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "if listener is not null");
                Intent intent = new Intent(context, FormActivity.class);
                intent.putExtra("podId", currentPod.id);
                context.startActivity(intent);
            }
        });

    }
}
