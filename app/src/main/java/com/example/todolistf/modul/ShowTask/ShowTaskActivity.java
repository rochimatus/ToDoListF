package com.example.todolistf.modul.ShowTask;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class ShowTaskActivity extends BaseFragmentHolderActivity {
    ShowTaskFragment showTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        showTaskFragment = new ShowTaskFragment();
        setCurrentFragment(showTaskFragment, false);

    }



}
