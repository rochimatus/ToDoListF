package com.example.todolistf.modul.edit;


import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;


public class EditActivity extends BaseFragmentHolderActivity {
    EditFragment editFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        editFragment = new EditFragment();
        setCurrentFragment(editFragment, false);

    }



}
