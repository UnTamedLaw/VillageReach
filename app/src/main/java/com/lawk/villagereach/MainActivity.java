package com.lawk.villagereach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

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
        //test code

    }


    public void onClick(View button) {
        Log.i(TAG, "Main activity button " + button.getId() + " clicked");
        EditText username = findViewById(R.id.username);
        String myUserName = username.getText().toString();
        //username.getText().clear();

        EditText password = findViewById(R.id.password);
        String myPassword = password.getText().toString();
        String mymessage = myUserName + myPassword;

        InternalStorageHandler.getInstance(this);
        //test code this will log in a user as administrator and password and ignore the fields for
        //testing convinience. change this to login(myUserName,myPassword, this) later!
        Log.i(TAG,"MainActivity: BEGIN LOGIN PROCEDURE");
        Login.login("administrator", "password", this, new AuthCallback() {
            String test = "Test";
            @Override
            public void onSuccess() {
                Log.i(TAG,"MainActivity: END LOGIN PROCEDURE: successfully logged in and maybe synced");
            }
            @Override
            public void onFailure(Exception error) {
                Log.i(TAG, "MainActivity: END LOGIN PROCEDURE: something went wrong");
                //code like if (error instanceof ExceptionType) should go here to make the UI react to any errors
            }
        });
        //test code

        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        startActivityForResult(intent, RESULT_ID);

    }


}
