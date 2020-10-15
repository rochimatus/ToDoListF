package com.example.todolistf.modul.show;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class ShowActivity extends BaseFragmentHolderActivity {
    ShowFragment showFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        showFragment = new ShowFragment();
        setCurrentFragment(showFragment, false);

    }



}
