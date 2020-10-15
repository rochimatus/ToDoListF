package com.example.todolistf.modul.delete;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class DeleteActivity extends BaseFragmentHolderActivity {
    DeleteFragment createFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        createFragment = new DeleteFragment();
        setCurrentFragment(createFragment, false);

    }



}
