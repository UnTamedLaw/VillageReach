package com.lawk.villagereach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Order> orderArrayList;
    private HashMap<String, Order> orderHashMap;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int id);
    }

    public OrderRecyclerAdapter(Context context, HashMap<String, Order> orderHashMap) {
        this.context = context;
        this.orderArrayList = new ArrayList<Order>(orderHashMap.values());
        this.orderHashMap = orderHashMap;
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

        Order currentOrder = orderArrayList.get(id);

        orderNumber.setText(currentOrder.orderCode);
        orderStatus.setText(currentOrder.status);
        requestingFacility.setText(currentOrder.requestingFacility.name);
        supplyingDepot.setText(currentOrder.supplyingFacility.name);

        //YO - I had to comment these out cuz they're missing from the schema. I'll add them back later.
        //program.setText(currentOrder.getProgram());
        //period.setText(currentOrder.getProcessingPeriod());
        //orderDate.setText(currentOrder.getCreatedDate());
        emergency.setText(currentOrder.emergency.toString());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(id);
                }
            }
        });
    }
}
