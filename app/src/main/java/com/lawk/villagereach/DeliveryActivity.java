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

public class DeliveryActivity extends AppCompatActivity {

    private static final String TAG = "MyTracker";
    private static String RESULT = "DeliveryResponse";
    private RecyclerView recyclerView;
    private OrderRecyclerAdapter orderRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        orderRecyclerAdapter = new OrderRecyclerAdapter(DeliveryActivity.this);
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
        Gson gson = new Gson();
        Credentials creds = gson.fromJson(InternalStorageHandler.getInstance(null).readFile("loginCredentials"), Credentials.class);
        Login.login(creds.username, creds.password, this, new AuthCallback() {
            @Override
            public void onSuccess() {
                Toast success = Toast.makeText(getApplicationContext(), "successfully synced", Toast.LENGTH_SHORT);
                success.show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Exception error) {
                Toast fail = Toast.makeText(getApplicationContext(), "sync failed", Toast.LENGTH_SHORT);
                fail.show();
            }
        });
    }
    public void formActivity(View view) {
        Intent intent = new Intent(DeliveryActivity.this, FormActivity.class);
        startActivity(intent);
    }
}