package com.lawk.villagereach;

public interface SyncCallback {
    void onSuccess();
    void onFailure(Exception error);
}