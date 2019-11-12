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

import com.android.volley.ClientError;


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

        final Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        //test code this will log in a user as administrator and password and ignore the fields for
        //testing convinience. change this to login(myUserName,myPassword, this) later!

        Log.i(TAG,"MainActivity: BEGIN LOGIN PROCEDURE");
        Login.login("administrator", "password1", this, new AuthCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG,"MainActivity: END LOGIN PROCEDURE: successfully logged in and maybe synced");
                startActivityForResult(intent, RESULT_ID);
            }
            @Override
            public void onFailure(Exception error) {
                Log.i(TAG, "MainActivity: END LOGIN PROCEDURE: something went wrong");
                if(error instanceof ClientError){
                    Log.i(TAG,"Client Error");
                    ClientError newError = (ClientError)error;
                    int responseCode = newError.networkResponse.statusCode;
                    Log.i(TAG,"Status Code:" + Integer.toString(responseCode));
                    if (responseCode == 400) {
                        Log.i(TAG,"Wrong Credentials");
                        Toast wrongCredentialsToast = Toast.makeText(getApplicationContext(),"Wrong credentials", Toast.LENGTH_SHORT);
                        wrongCredentialsToast.show();
                    }


                }
                //code like if (error instanceof ExceptionType) should go here to make the UI react to any errors
            }
        });
        //test code




    }


}
