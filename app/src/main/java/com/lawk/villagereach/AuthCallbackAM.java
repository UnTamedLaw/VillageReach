package com.lawk.villagereach;

import com.android.volley.VolleyError;

public interface AuthCallbackAM {
    void onSuccess(String token);
    void onFailure(VolleyError error);
}
