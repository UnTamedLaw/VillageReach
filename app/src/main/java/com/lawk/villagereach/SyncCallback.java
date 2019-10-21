package com.lawk.villagereach;

public interface SyncCallback {
    void onSuccess(Order[] result);
    void onFailure(Exception error);
}