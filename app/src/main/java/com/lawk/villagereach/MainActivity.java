package com.lawk.villagereach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.ClientError;
import com.android.volley.NoConnectionError;


public class MainActivity extends AppCompatActivity {
    

    private static final String TAG = "myTracker";
    private static final int RESULT_ID = 1;
    public static final String MESSAGE_ID = "loginInfo";
    Button b1;
    EditText username;
    EditText password;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        b1 = (Button) findViewById(R.id.loginButton);
        this.spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
    }

    public void onClick(View button) {
        Log.i(TAG, "Main activity button " + button.getId() + " clicked");
        String myUserName = username.getText().toString();
        String myPassword = password.getText().toString();
        if (myUserName.equals("") || myPassword.equals("")) {
            Toast emptyCredsToast = Toast.makeText(getApplicationContext(), "Username or Password field is empty", Toast.LENGTH_SHORT);
            emptyCredsToast.show();
        } else {
            lockScreenForLoading();


            InternalStorageHandler.getInstance(this);

            final Intent intent = new Intent(this, DeliveryActivity.class);
            Log.i(TAG, "MainActivity: BEGIN LOGIN PROCEDURE");
            //lockscreenForLoading()
            //visible
            Login.login(myUserName, myPassword, this, new AuthCallback() {
                @Override
                public void onSuccess() {
                    unlockScreenForLoading();
                    //invisible
                    Log.i(TAG, "MainActivity: END LOGIN PROCEDURE: successfully logged in and maybe synced");
                    startActivityForResult(intent, RESULT_ID);
                    finish();
                }

                @Override
                public void onFailure(Exception error) {
                    unlockScreenForLoading();
                    Log.i(TAG, "MainActivity: END LOGIN PROCEDURE: something went wrong");
                    if (error instanceof ClientError) {
                        Log.i(TAG, "Client Error");
                        ClientError newError = (ClientError) error;
                        int responseCode = newError.networkResponse.statusCode;
                        Log.i(TAG, "Status Code:" + Integer.toString(responseCode));
                        if (responseCode == 400) {
                            Log.i(TAG, "Wrong Credentials");
                            Toast wrongCredentialsToast = Toast.makeText(getApplicationContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT);
                            wrongCredentialsToast.show();
                        }
                    } else if (error instanceof NoConnectionError) {
                        Log.i(TAG, "Connection to server failed");
                        Toast noConnectionErrorToast = Toast.makeText(getApplicationContext(), "Connection to server failed", Toast.LENGTH_SHORT);
                        noConnectionErrorToast.show();
                    }
                }
            });
        }
    }

    private void lockScreenForLoading() {
        spinner.setVisibility(View.VISIBLE);
        //disable the buttons
        b1.setEnabled(false);
        //disable text field
        password.setEnabled(false);
        username.setEnabled(false);
        //make the spinner

    }

    private void unlockScreenForLoading() {
        spinner.setVisibility(View.GONE);
        //remove the spinner
        //enable the buttons
        b1.setEnabled(true);
        //enable text field
        password.setEnabled(true);
        username.setEnabled(true);

    }
}