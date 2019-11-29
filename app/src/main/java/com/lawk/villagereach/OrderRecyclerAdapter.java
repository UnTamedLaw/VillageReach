package com.lawk.villagereach;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    private static final String TAG = "MyTracker";
    private Context context;
    private ArrayList<Order> orderArrayList;
    private ArrayList<ProofOfDelivery> podArrayList;
    private HashMap<String, Order> orderHashMap;
    private HashMap<String, ProofOfDelivery> podHashMap;
    private HashMap<String, Shipment> shipmentHashMap;

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
        public CardView layout;
        public TextView orderNumber;
        public TextView orderStatus;
        public TextView requestingFacility;
        public TextView supplyingDepot;
        public TextView program;
        public TextView period;
        public TextView orderDate;
        public TextView emergency;
        public Button formClick;
        public ProofOfDelivery currentPod;

        public ViewHolder(final CardView v) {
            super(v);
            layout = v;
            this.orderNumber = v.findViewById(R.id.order_code);
            this.orderStatus = v.findViewById(R.id.status_label);
            this.requestingFacility = v.findViewById(R.id.requesting_facility);
            this.supplyingDepot = v.findViewById(R.id.supplying_depot);
            this.program = v.findViewById(R.id.program);
            this.period = v.findViewById(R.id.period);
            this.orderDate = v.findViewById(R.id.order_date);
            this.emergency = v.findViewById(R.id.emergency);
            this.formClick = v.findViewById(R.id.formClick);
            formClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "if listener is not null");
                    Intent intent = new Intent(v.getContext(), FormActivity.class);
                    intent.putExtra("podId", currentPod.id);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void setPod(ProofOfDelivery pod) {
            currentPod = pod;
        }
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_recycler_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int id) {
        final ProofOfDelivery currentPod = podArrayList.get(id);
        final Shipment currentShipment = shipmentHashMap.get(currentPod.shipment.id);
        final Order currentOrder = currentShipment.order;
        holder.setPod(currentPod);

        holder.orderNumber.setText(currentOrder.orderCode);
        holder.orderStatus.setText(currentOrder.status);
        holder.requestingFacility.setText(currentOrder.requestingFacility.name);
        holder.supplyingDepot.setText(currentOrder.supplyingFacility.name);
        if (currentOrder.program != null) {
            holder.program.setText(currentOrder.program.name);
        }
        if (currentOrder.processingPeriod != null) {
            holder.period.setText(currentOrder.processingPeriod.name);
        }
        holder.orderDate.setText(currentOrder.createdDate);
        holder.emergency.setText(currentOrder.emergency.toString());

        holder.formClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "if listener is not null");
                Intent intent = new Intent(context, FormActivity.class);
                intent.putExtra("podId", currentPod.id);
                context.startActivity(intent);
            }
        });

        if (currentPod.status.equals("CONFIRMED")) {
            holder.layout.setCardBackgroundColor(Color.GRAY);
        }
    }
}
