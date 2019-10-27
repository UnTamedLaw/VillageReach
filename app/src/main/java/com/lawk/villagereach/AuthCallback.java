package com.lawk.villagereach;

public interface AuthCallback {
    void onSuccess();
    void onFailure(Exception error);
}