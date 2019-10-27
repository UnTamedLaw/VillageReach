package com.lawk.villagereach;

public interface AuthCallback {
    void onSuccess(String token);
    void onFailure(Exception error);
}