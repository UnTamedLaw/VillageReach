package com.lawk.villagereach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    private Listener listener;

    public void  setListener(Listener listener) {
        this.listener = listener;
    }
    interface Listener {
        void onClick(int id);
    }

    private Order[] orders;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    @Override
    public int getItemCount() {
        return orders.length;
    }

    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_recycler_card, parent, false);
        return  new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final  int id) {
        CardView cardView = holder.layout;
        TextView orderNumber = cardView.findViewById(R.id.order_code);
        TextView orderStatus = cardView.findViewById(R.id.status_label);
        TextView requestingFacility = cardView.findViewById(R.id.requesting_facility);
        TextView supplyingDepot = cardView.findViewById(R.id.supplying_depot);
        TextView program = cardView.findViewById(R.id.program);
        TextView period = cardView.findViewById(R.id.period);
        TextView orderDate = cardView.findViewById(R.id.order_date);
        TextView emergency = cardView.findViewById(R.id.emergency);

        Order order = orders[id];

        orderNumber.setText(order.getOrdercode());
        orderStatus.setText(order.getStatus());
        requestingFacility.setText(order.getRequestingFacility());
        supplyingDepot.setText(order.getSupplyingFacility());
        program.setText(order.getProgram());
        period.setText(order.getProcessingPeriod());
        orderDate.setText(order.getCreatedDate());
        emergency.setText(order.getEmergency().toString());

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






























































