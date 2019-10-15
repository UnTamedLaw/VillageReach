package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static final String KEY = FormActivity.class.toString();

    private static final String TAG = "Second Activity Button";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent intent = getIntent();
        String messageSend = intent.getStringExtra(EXTRA_MESSAGE);
        TextView messageString = (TextView) findViewById(R.id.message);
        messageString.setText(messageSend);
        Log.i(TAG,  "Started ");
    }

    public void onClickRespond(View view) {
        Log.i(TAG,  "Button " + view.getId() + " clicked.");
        Intent intent = new Intent(this, DeliveryActivity.class);
        startActivity(intent);
        finish();

    }
}