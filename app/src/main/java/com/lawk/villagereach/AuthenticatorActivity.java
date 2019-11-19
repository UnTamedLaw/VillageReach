package com.lawk.villagereach;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.net.Network;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;


public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    public final static String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String AUTH_TYPE = "AUTH_TYPE";
    public final static String ACCOUNT_NAME = "ACCOUNT NAME";
    public final static String IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

    public static final String LOGIN_FAILED_MESSAGE = "Login Failed";

    public static final String MESSAGE_ID = "Credentials: ";

    public static final int RESULT_ID = 1;

    public final static String USER_PASS = "USER_PASS";

    private final String TAG = "Auth Activity Says: ";

    private AccountManager accountManager;
    private String authTokenType;
    //private LoginTask loginTask = null;
    private String username;
    private String password;
    private Intent startingIntent;

    private EditText emailView;
    private EditText passView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Activity Created");
        // Gets an instance of AccountManager. Passes the current context from outer class.
        accountManager = accountManager.get(getBaseContext());
        startingIntent = getIntent();

        if (startingIntent.hasExtra(LOGIN_FAILED_MESSAGE)) {
            Toast.makeText(this, startingIntent.getStringExtra(LOGIN_FAILED_MESSAGE), Toast.LENGTH_SHORT).show();
        }

        /*
        *
        * get the data from whatever Intent launched this activity.
        * e.g if user clicks add account from the Settings->Accounts screen of the OS,
        * it will get that data that was passed along with that intent to launch
        * this activity. For clarity, the above situation gets accountType
        * from the authenticator.xml file in resources. The Flow follows
        *
        * User clicks through to Settings->Accounts->Add Account.
        *
        * Since VillageReachAuthenticator is registered as a service in our AndroidManifext.xml file,
        * it will show up in the list of accounts you can add. It pulls the necessary
        * labels/text from xml/authenticator.xml
        *
        * */
        String accountName = getIntent().getStringExtra(ACCOUNT_NAME);
        authTokenType = getIntent().getStringExtra(AUTH_TYPE);

        // if no type from launching intent, set intent to static val in AccountUtil
        if (authTokenType == null){
            authTokenType = AccountUtil.AUTH_TOKEN_TYPE;
        }

        if(accountName != null){
            ((TextView)findViewById(R.id.username)).setText(accountName);
        }

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Login.login("administrator", "password", getApplicationContext());
                login();
            }
        });
    }

    private void login() {

        // will be used for form validation. if username or password don't pass validation
        // cancel will be set to true and the login process will be stopped.
        // will need to set up a view to display login status.
        boolean cancel = false;

        username = ((TextView) findViewById(R.id.username)).getText().toString();
        password = ((TextView) findViewById(R.id.password)).getText().toString();

        //
        if(cancel) {
            Toast.makeText(getBaseContext(), "Username or Password Invalid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Attempting Login", Toast.LENGTH_SHORT).show();
            submit();
        }

    }

    /*
    *
    * Here is where we check if phone has interwebs. Need to add another condition that checks
    * the entered username and password against 
    *
    * */

    private void submit() {

        final String userName = ((TextView) findViewById(R.id.username)).getText().toString();
        final String password = ((TextView) findViewById(R.id.password)).getText().toString();
        final String accountType = getIntent().getStringExtra(ACCOUNT_TYPE);

        final Credentials creds = new Credentials(userName, password, accountType);
        final Intent intent = new Intent();

        if (NetworkingTest.isConnected(getApplicationContext())) {

            if(accountType != null) {
                accountManager.invalidateAuthToken(accountType, null);
            }
            Auth.authenticateAM(creds, getApplicationContext(), new AuthCallbackAM() {
                @Override
                public void onSuccess(String token) {

                    intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, creds.username);
                    intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, AccountUtil.ACCOUNT_TYPE);
                    intent.putExtra(AccountManager.KEY_AUTHTOKEN, token);
                    intent.putExtra(USER_PASS, creds.password);
                    Log.i(TAG, AccountManager.KEY_AUTHTOKEN);
                    finishLogin(intent);

                }

                @Override
                public void onFailure(VolleyError error) {
                    Log.i(TAG, "Network Error: " + String.valueOf(error.networkResponse.statusCode));
                    intent.putExtra(KEY_ERROR_MESSAGE, "Network Error: " + error.networkResponse.statusCode);
                    failedLogin(intent);
                }
            });





            /*
             *   todo: If no account exists in AccountManager, add an account, and send a token request and store in AM.
             *    Use conceal to encrypt password. If no internet and userName/password matches what is in AccountManager, launch delivery activity.
             *    if internet is available, and account exists in account manager send token request. Get new token and store in AccountManager
             *    this will use whatever authentication service we have set up.
             *
             *
             */
        }
    }

    public void failedLogin(Intent intent) {
        intent.setClass(getApplicationContext(), AuthenticatorActivity.class);
        intent.putExtra(LOGIN_FAILED_MESSAGE, "Login Failed, Username or Password invalid");
        finish();
        startActivity(intent);

    }


    /*
    *
    *
    * If
    *
    */

    public void finishLogin(Intent intent) {

        Log.i(TAG, "Finish Login Started");
        Log.i(TAG, intent.getStringExtra(AccountManager.KEY_AUTHTOKEN));

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        Log.i(TAG, accountName);
        String password = intent.getStringExtra(USER_PASS);
        Log.i(TAG, password);
        final Account account = new Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));

        /*
        *
        *
        * This check only passes if the AuthenticatorActivity is launched from android OS Accounts > Add Account
        *
        * This is because the the intent that launches the activity from there sets IS_ADDING_NEW_ACCOUNT to true
        *
        * Otherwise, only set password
        *
        */
        if (getIntent().getBooleanExtra(IS_ADDING_NEW_ACCOUNT, false)) {
            Log.i(TAG, "addAccountExplicitly");
            String authToken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authTokenType = AccountUtil.AUTH_TOKEN_TYPE;

            accountManager.addAccountExplicitly(account, password, null);
            accountManager.setAuthToken(account, authTokenType, authToken);
        } else {
            Log.i(TAG, "Set Password Only");
            accountManager.setPassword(account, password);
        }

    }


    class LoginTask extends AsyncTask<String, Void, Intent> {

            @Override
            protected Intent doInBackground(String... params) {

                final Credentials credentials = new Credentials(params[0], params[1], params[2]);
                final Intent intent = new Intent();

                if (NetworkingTest.isConnected(getApplicationContext())) {

                    Auth.authenticateAM(credentials, getApplicationContext(), new AuthCallbackAM() {
                        @Override
                        public void onSuccess(String token) {

                            intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, credentials.username);
                            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, AccountUtil.ACCOUNT_TYPE);
                            intent.putExtra(AccountManager.KEY_AUTHTOKEN, token);
                            intent.putExtra(USER_PASS, credentials.password);
                            Log.i(TAG, AccountManager.KEY_AUTHTOKEN);
                        }

                        @Override
                        public void onFailure(VolleyError error) {
                            Log.i(TAG, "Network Error: " + String.valueOf(error.networkResponse.statusCode));
                            intent.putExtra(KEY_ERROR_MESSAGE, "Network Error: " + error.networkResponse.statusCode);
                        }
                    });
                    return intent;
                }

                // if network connection is not connected, check credentials against what is stored in account Manager


                //if (AccountManager.get){
                return null;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra(KEY_ERROR_MESSAGE) || intent.getExtras() == null) {
                    Log.i(TAG, "Intent has Error Message");
                    //loginTask = null;
                    failedLogin(intent);
                } else {
                    Log.i(TAG, "Token = " + intent.getStringExtra(AccountManager.KEY_AUTHTOKEN));
                    //finishLogin(intent);
                    Log.i(TAG, "authtoken should have logged");
                }
            }
        }

//        String mymessage = username + password;
//
//        Intent intent2 = new Intent(this, DeliveryActivity.class);
//        intent2.putExtra(MESSAGE_ID, mymessage);
//        startActivityForResult(intent2, RESULT_ID);
//
   }

//}
