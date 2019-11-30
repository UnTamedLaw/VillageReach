package com.lawk.villagereach;

import com.android.volley.VolleyError;

public interface StringCallback {
    void onSuccess(String result);
    void onFailure(VolleyError error);
}
