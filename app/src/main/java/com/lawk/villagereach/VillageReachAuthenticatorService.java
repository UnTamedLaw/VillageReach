package com.lawk.villagereach;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*
*
*
*
* This allows other processes to bind with the service that we registered
* in our manifest.
*
* this implementation automagically takes care of calling the authenticator's
* methods from external processes. Don't fully understand, but we need it so we can
* add accounts from the Android OS Account manager.
*
*
*/

public class VillageReachAuthenticatorService extends Service {

    @Override
    public IBinder onBind(Intent intent) {

        VillageReachAuthenticator authenticator = new VillageReachAuthenticator(this);
        return authenticator.getIBinder();

    }

}
