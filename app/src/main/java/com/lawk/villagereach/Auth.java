package com.lawk.villagereach;

public class Auth {
    public static void authenticate(Credentials creds) {
        if (creds == null) {
            //if storage has creds
                //creds = storage's creds
            //else
                //throw error
        }
        //InternalStorageHandler.setToken(this.requestToken(creds))
    }

    private String requestToken(Credentials creds) {
        //volley stuff to get string
        String token = "bearer" + "token string goes here";
        return token;
    }
}
