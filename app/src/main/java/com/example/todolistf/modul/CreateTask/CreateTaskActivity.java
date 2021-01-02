package com.example.todolistf.modul.CreateTask;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class CreateTaskActivity extends BaseFragmentHolderActivity {
    CreateTaskFragment createTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        createTaskFragment = new CreateTaskFragment();
        setCurrentFragment(createTaskFragment, false);

    }



}
