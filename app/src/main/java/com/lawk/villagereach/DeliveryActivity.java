package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DeliveryActivity extends AppCompatActivity {
    private static final String TAG= "Form Activity Button";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

    }

    public void formActivity(View view) {
        Intent intent = new Intent(DeliveryActivity.this, FormActivity.class);
        startActivity(intent);
    }

}
