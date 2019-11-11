package com.lawk.villagereach;

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


public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    public final static String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String AUTH_TYPE = "AUTH_TYPE";
    public final static String ACCOUNT_NAME = "ACCOUNT NAME";
    public final static String IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

    public static final String MESSAGE_ID = "Credentials: ";

    public static final int RESULT_ID = 1;

    public final static String PARAM_USER_PASS = "USER_PASS";

    private final String TAG = "Authenticator Activity Says: ";

    private AccountManager accountManager;
    private String authTokenType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Gets an instance of AccountManager. Passes the current context from outer class.
        accountManager = accountManager.get(getBaseContext());

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
                submit();
            }
        });
    }


    public void submit() {

        final String userName = ((TextView) findViewById(R.id.username)).getText().toString();
        final String password = ((TextView) findViewById(R.id.password)).getText().toString();
        final String accountType = getIntent().getStringExtra(ACCOUNT_TYPE);

        final Credentials creds = new Credentials(userName, password, accountType);

        /*
         *   todo: If no account exists in AccountManager, add an account, and send a token request and store in AM.
         *    Use conceal to encrypt password. If no internet and userName/password matches what is in AccountManager, launch delivery activity.
         *    if internet is available, and account exists in account manager send token request. Get new token and store in AccountManager
         *    this will use whatever authentication service we have set up.
         *
         *
         */

        class LoginClass extends AsyncTask<String, Void, Intent>{

            @Override
            protected Intent doInBackground(String... params){



                String authToken;
                Bundle bundleForAccountManager = new Bundle();

                try {

                    if (NetworkingTest.isConnected(getBaseContext())) {
                        //Auth.authenticate(creds, getBaseContext());

                    }
                } catch (Exception e) {

                }

                return null;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                    Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
                } else {
                    //finishLogin(intent);
                }
            }
        };

        String mymessage = userName + password;

        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        startActivityForResult(intent, RESULT_ID);

    }

}
