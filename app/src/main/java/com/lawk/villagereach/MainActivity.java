package com.lawk.villagereach;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
=======


>>>>>>> 17c53f4061d1924d51cbffddb00815293ea3b3c4
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
=======
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

private static final String TAG = "myTracker";
private static final int RESULT_ID = 1;
public static final String MESSAGE_ID = "loginInfo";


import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
>>>>>>> 17c53f4061d1924d51cbffddb00815293ea3b3c4

    private Toolbar topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggleIcon = new ActionBarDrawerToggle(this,
                drawer,
                topToolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggleIcon);
        toggleIcon.syncState();

        Fragment fragment = new DeliveryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_fragments, fragment);
        transaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch(id) {
            case R.id.nav_delivery:
                fragment = new DeliveryFragment();
                break;
            case R.id.nav_sync:
                fragment = new SyncFragment();
                break;
            default:
                fragment = new DeliveryFragment();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragments, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View button) {

        Log.i(TAG, "Main activity button " + button.getId() + " clicked");

        EditText username = findViewById(R.id.username);
        String myUserName = username.getText().toString();
        //username.getText().clear();

        EditText password = findViewById(R.id.password);
        String myPassword = password.getText().toString();
        //password.getText().clear();

        String mymessage = myUserName + myPassword;

        Intent intent = new Intent(this, DeliveryActivity.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        startActivityForResult(intent, RESULT_ID);
    }

}
