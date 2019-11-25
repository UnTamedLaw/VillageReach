package com.lawk.villagereach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.ClientError;
import com.android.volley.NoConnectionError;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTracker";
    private static final int RESULT_ID = 1;
    public static final String MESSAGE_ID = "loginInfo";
    private ArrayList<Login> loginArrayList;
    Button b1;
    EditText userName;
    EditText password;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginArrayList = new ArrayList<>();
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        b1 = (Button) findViewById(R.id.loginButton);
        this.spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        userName.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
    }
    //TextWatcher to enable/disable button
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String username = userName.getText().toString().trim();
            String Password = password.getText().toString().trim();

            b1.setEnabled(!username.isEmpty() && !Password.isEmpty());
            //Toast.makeText(MainActivity.this, "Field can't be empty", Toast.LENGTH_LONG).show();
        }
        @Override
        public void afterTextChanged(Editable a) {

        }
    };

    public void onClick(View button) {
        Log.i(TAG, "Main activity button " + button.getId() + " clicked");
        lockScreenForLoading();
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
        //lockscreenForLoading()
        //visible
        Login.login("administrator", "password", this, new AuthCallback() {
            @Override
            public void onSuccess() {
                //unlocksscreenforloading()
                //invisible
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
                        Toast wrongCredentialsToast = Toast.makeText(getApplicationContext(),"Invalid Login Credentials", Toast.LENGTH_SHORT);
                        wrongCredentialsToast.show();
                    }
                }
                else if(error instanceof NoConnectionError) {
                    Log.i(TAG, "Connection to server failed");
                    Toast noConnectionErrorToast = Toast.makeText(getApplicationContext(),"Connection to server failed", Toast.LENGTH_SHORT);
                    noConnectionErrorToast.show();
                }
                if (error.getMessage().equals("offLineLoginFail")){
                    Log.i(TAG, "offLineLoginFail");
                    Toast offLineLoginFailToast = Toast.makeText(getApplicationContext(),"Offline Login Failed", Toast.LENGTH_SHORT);
                    offLineLoginFailToast.show();
                }
            }
        });
        //test code
    }

    public void lockScreenForLoading(){
        spinner.setVisibility(View.VISIBLE);
        //make the spinner
        //disable the buttons
    }
    public void unlockScreenForLoading(){
        spinner.setVisibility(View.GONE);
        //remove the spinner
        //enable the buttons
    }


}
