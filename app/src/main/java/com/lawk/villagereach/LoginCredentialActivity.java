package com.lawk.villagereach;
//importing conceal to manage username and password encryption and decryption

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.facebook.soloader.SoLoader;


public class LoginCredentialActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoLoader.init(this, false);
    }
}
