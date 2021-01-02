package com.example.todolistf.modul.Home;


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

        homeFragment = new HomeFragment();

        setCurrentFragment(homeFragment, false);
    }



}
