package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryActivity extends AppCompatActivity {

    private static final String TAG = "Anu";
    private static String RESULT = "DeliveryResponse";
    private RecyclerView recyclerView;
    private OrderRecyclerAdapter orderRecyclerAdapter;
    //private ArrayList<Order> orderModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //OrderModel model = new OrderModel();
        //orderModelArrayList = model.grabJson();


        String orderHashMapString = InternalStorageHandler.getInstance(this).readFile("orderMap");
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Order>>(){}.getType();
        HashMap<String, Order> orderHashMap = gson.fromJson(orderHashMapString, type);
        orderRecyclerAdapter = new OrderRecyclerAdapter(DeliveryActivity.this, orderHashMap);
        recyclerView.setAdapter(orderRecyclerAdapter);
        Button getDeliveries = findViewById(R.id.delivery);
        Button getSync = findViewById(R.id.sync);

    }

    public void showDelivery(View view) {
        Toast delivery = Toast.makeText(getApplicationContext(), "No Network Connection! Deliveries Can't Be Complete!",
                Toast.LENGTH_SHORT);
        delivery.show();
    }

    public void showSync(View view) {
        Toast sync = Toast.makeText(getApplicationContext(), "No Network Connection! Sync Can't Be Completed!",
                Toast.LENGTH_SHORT);
        sync.show();

    }

    public void formActivity(View view) {
        Intent intent = new Intent(DeliveryActivity.this, FormActivity.class);
        startActivity(intent);
    }
}