package com.lawk.villagereach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


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

       EditText username = findViewById(R.id.username);
       String myUserName = username.getText().toString();

       EditText password = findViewById(R.id.password);
       String myPassword = password.getText().toString();

       String  vars = "vars";
       loginArrayList.add(new Login(myUserName, myPassword));

   }


}
