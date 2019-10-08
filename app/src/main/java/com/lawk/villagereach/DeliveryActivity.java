package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {
    private static final String TAG= "Form Activity Button";

    private static final int RECEIVE_MESSAGE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Log.i(TAG, this.getLifecycle()
                .getCurrentState()
                .toString());

    }

    public void SendMessage(View view) {

        Log.i(TAG, "Button" + view.getId() + " clicked. ");

        EditText messageSend=(EditText)findViewById(R.id.message);
        String messageString = messageSend.getText().toString();

        Intent intent = new Intent (this, FormActivity.class);
        intent.putExtra(FormActivity.EXTRA_MESSAGE, messageString);
        startActivityForResult(intent, RECEIVE_MESSAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == RECEIVE_MESSAGE_ACTIVITY_REQUEST_CODE  ) {
            if (resultCode == RESULT_OK) {
                String response = data.getStringExtra(FormActivity.KEY);

                TextView textView = (TextView)findViewById(R.id.responseText);
                textView.setText(response);
            }
        }
    }
}
