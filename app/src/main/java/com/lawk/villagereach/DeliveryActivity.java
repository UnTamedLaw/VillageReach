package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {

    private static final String TAG = "deliveryTracker";
    public static final String RESULT = "my.response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Log.i(TAG, "Second activity created");

        Intent intent = getIntent();
        String myMessage = intent.getStringExtra((MainActivity.MESSAGE_ID));


        TextView label = (TextView)findViewById(R.id.delivery_text);
            label.setText(myMessage);
    }


}
