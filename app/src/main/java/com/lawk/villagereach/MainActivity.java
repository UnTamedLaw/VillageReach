package com.lawk.villagereach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTracker";
    private static final int RESULT_ID = 1;
    public static final String MESSAGE_ID = "loginInfo";
    private ArrayList<Login> loginArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginArrayList = new ArrayList<>();
    }


    public void onClick(View button) {
        Log.i(TAG, "Main activity button " + button.getId() + " clicked");
        //test code
        Auth auth = new Auth(this);
        //test code
        EditText username = findViewById(R.id.username);
        String myUserName = username.getText().toString();
        //username.getText().clear();

        EditText password = findViewById(R.id.password);
        String myPassword = password.getText().toString();
        String mymessage = myUserName + myPassword;
        
        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        startActivityForResult(intent, RESULT_ID);
        loginArrayList.add(new Login(myUserName, myPassword));

   }


}
