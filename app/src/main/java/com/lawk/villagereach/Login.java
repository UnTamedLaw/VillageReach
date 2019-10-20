package com.lawk.villagereach;

import android.content.Context;

public class Login {
    public Login() {

    }

    public static void login(String username, String password, Context context) {
        Credentials creds = new Credentials(username, password);
        Auth.authenticate(creds, context);
        Sync.sync(context);
    }
}
