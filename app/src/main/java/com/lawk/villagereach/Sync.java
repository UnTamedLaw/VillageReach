package com.lawk.villagereach;
import android.content.Context;

public class Sync {
    public void Sync(Context context) {
        if (Networking.isConnected(context)) {
            sendDrafts();
            requestData();
        }
    }

    private void sendDrafts() {
        //request to send off drafts
    }

    private void requestData() {
        //request to get order data from serv

    }
}
