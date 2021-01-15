package com.example.todolistf.modul.Register;

import android.view.View;

import com.example.todolistf.base.BaseFragmentHolderActivity;
import com.example.todolistf.modul.Login.LoginFragment;

public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        registerFragment = new RegisterFragment();

        setCurrentFragment(registerFragment, false);
    }
}
