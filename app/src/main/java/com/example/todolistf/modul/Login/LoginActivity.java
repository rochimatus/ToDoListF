package com.example.todolistf.modul.Login;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistf.base.BaseActivity;
import com.example.todolistf.base.BaseFragmentHolderActivity;
import com.example.todolistf.modul.Home.HomeFragment;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        loginFragment = new LoginFragment();

        setCurrentFragment(loginFragment, false);
    }
}
