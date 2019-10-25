package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        OrderModel model = new OrderModel();

        model.grabJson();
    }
    public void formActivity(View view) {
        Intent intent = new Intent(DeliveryActivity.this, FormActivity.class);
        startActivity(intent);
    }
}