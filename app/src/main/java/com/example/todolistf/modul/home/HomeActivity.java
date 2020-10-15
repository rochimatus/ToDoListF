package com.example.todolistf.modul.home;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        int status = getIntent().getExtras().getInt("status");
        homeFragment = new HomeFragment(status);

        setCurrentFragment(homeFragment, false);
    }



}
