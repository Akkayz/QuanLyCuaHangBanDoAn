package com.example.quanlycuahangbandoan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.quanlycuahangbandoan.R;
import com.example.quanlycuahangbandoan.db.DatabaseHandler;
import com.example.quanlycuahangbandoan.fracment.CartFragment;
import com.example.quanlycuahangbandoan.fracment.CategoryProductFragment;
import com.example.quanlycuahangbandoan.fracment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    // database
    DatabaseHandler db;
    Bundle bundle;
    public static final String KEY_USER_TO_MAIN = "KEY_USER_TO_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        String idUser = intent.getStringExtra(KEY_USER_TO_MAIN);
        String idRole = intent.getStringExtra("idRole");
        bundle = new Bundle();
        bundle.putString("iduser", idUser);
        bundle.putString("idRole", idRole);

        // load lúc đầu
        Fragment fragment;
        fragment = new CategoryProductFragment();
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // back to screen
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Display confirmation here, finish() activity.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null);
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_product) {
                fragment = new CategoryProductFragment();
                fragment.setArguments(bundle);
                loadFragment(fragment);
                return true;
            } else if (itemId == R.id.navigation_cart) {
                fragment = new CartFragment();
                fragment.setArguments(bundle);
                loadFragment(fragment);
                return true;
            } else if (itemId == R.id.navigation_profile) {
                fragment = new ProfileFragment();
                fragment.setArguments(bundle);
                loadFragment(fragment);
                return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}