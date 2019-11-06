package com.lawk.villagereach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class ProofOfDeliveryRecyclerAdaptor extends RecyclerView.Adapter<ProofOfDeliveryRecyclerAdaptor.ViewHolder>{

    private Context context;
    private ArrayList<ProofOfDelivery> podArrayList;
    private HashMap<String, ProofOfDelivery> podHashMap;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int id);
    }

    public ProofOfDeliveryRecyclerAdaptor(Context context, HashMap<String, ProofOfDelivery> podHashMap) {
        this.context = context;
        this.podArrayList = new ArrayList<ProofOfDelivery>(podHashMap.values());
        this.podHashMap = podHashMap;
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
        return podArrayList.size();
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
        TextView productUnit = cardView.findViewById(R.id.product_unit);
        TextView quantityOrdered = cardView.findViewById(R.id.quantity_ordered);
        TextView quantityShipped = cardView.findViewById(R.id.quantity_shipped);
        TextView lotCode = cardView.findViewById(R.id.lot_code);
        EditText quantityAccepted = cardView.findViewById(R.id.quantity_accepted);
        TextView quantityReturned = cardView.findViewById(R.id.quantity_rejected);
        //Spinner rejectionReason = cardView.findViewById(R.id.dynamic_spinner_for_rejection_reason);
        EditText notes = cardView.findViewById(R.id.notes);

        ProofOfDelivery pod = podArrayList.get(id);

        productName.setText("t");
        productUnit.setText("t");
        quantityOrdered.setText("t");
        quantityShipped.setText("5");
        lotCode.setText("5");
        quantityAccepted.setText("4");
        quantityReturned.setText("3");
        notes.setText("g");

    }
}