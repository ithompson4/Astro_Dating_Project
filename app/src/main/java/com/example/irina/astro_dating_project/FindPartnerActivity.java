package com.example.irina.astro_dating_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class FindPartnerActivity extends AppCompatActivity {

    FrameLayout fragmentLayout;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_partner);

        fragmentLayout = (FrameLayout) findViewById(R.id.fragment_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentFragment == null) {
            currentFragment = new ChooseOptionFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.fragment_layout, currentFragment).commit();
        }
    }

    public void setCurrentFragment(Fragment currentFragment) {
        count = 0;
        this.currentFragment = currentFragment;
    }

    // create  options menu to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_profile) {
            Intent aboutIntent = new Intent(this, ProfileActivity.class);
            startActivity(aboutIntent);
        }
        else if (id == R.id.mn_delete) {
            Intent deleteIntent = new Intent(this, DeleteProfileActivity.class);
            startActivity(deleteIntent);
        }
        else if (id == R.id.mn_edit) {
            Intent editIntent = new Intent(this, EditProfileActivity.class);
            startActivity(editIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    int count = 0;

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof ChooseOptionFragment) {
            if (count == 0) {
                Toast.makeText(this, "Press back again to close", Toast.LENGTH_SHORT).show();
                count++;
            } else
                finish();
        } else {
            super.onBackPressed();
        }
    }
}