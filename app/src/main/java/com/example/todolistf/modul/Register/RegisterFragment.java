package com.example.todolistf.modul.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.source.session.UserSessionRepository;
import com.example.todolistf.modul.Home.HomeActivity;
import com.example.todolistf.modul.Login.LoginPresenter;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View, View.OnClickListener {
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button btRegister;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, new UserSessionRepository(getActivity()));
        mPresenter.start();
        etEmail = fragmentView.findViewById(R.id.etEmail);
        etPassword = fragmentView.findViewById(R.id.etPassword);
        etConfirmPassword = fragmentView.findViewById(R.id.etConfirmPassword);
        btRegister = fragmentView.findViewById(R.id.btRegister);

        btRegister.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void setPresenter(RegisterContract presenter) {

    }

    @Override
    public void redirectHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.finishAffinity();
        startActivity(intent);
    }

    @Override
    public void showSuccess() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.finishAffinity();
        startActivity(intent);
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btRegister.getId())
            onRegister();
    }

    private void onRegister() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        mPresenter.performRegister(email, password, confirmPassword);
    }
}
