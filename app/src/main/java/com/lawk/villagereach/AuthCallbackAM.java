package com.lawk.villagereach;

public interface AuthCallbackAM {
    void onSuccess(String token);
    void onFailure(Exception error);
}
