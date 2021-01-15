package com.example.todolistf.modul.Home;


import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragmentHolderActivity;


public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    private final int UPDATE_REQUEST = 2019;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initializeFragment() {
        initializeView();
        setActionBar(tbDefault);
        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.VISIBLE);
        ivIcon.setVisibility(View.VISIBLE);

        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        MenuItem item = menu.findItem(R.id.email);
        item.setTitle(homeFragment.getEmail());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                System.out.println("was here");
                homeFragment.performLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
