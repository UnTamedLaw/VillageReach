package com.lawk.villagereach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity {

    private Toolbar topToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        Fragment fragment = new DeliveryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_fragments, fragment);
        transaction.commit();

    }


}
