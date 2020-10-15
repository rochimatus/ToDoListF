package com.example.todolistf.modul.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;

;

/**
 * Created by fahrul on 13/03/19.
 */

public class DeleteFragment extends BaseFragment<DeleteActivity, DeleteContract.Presenter> implements DeleteContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    static final int REQUEST_CODE = 901;

    public DeleteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_notify_delete, container, false);
        mPresenter = new DeletePresenter(this);
        mPresenter.start();

//        etEmail = fragmentView.findViewById(R.id.et_email);
//        etPassword = fragmentView.findViewById(R.id.et_password);
//        btnLogin = fragmentView.findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        setTitle("My Login View");

        return fragmentView;
    }

    public void setBtLoginClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email,password);
    }

    @Override
    public void setPresenter(DeleteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToProfile(String email, String password) {
//            Intent intent = new Intent(activity, ProfileActivity.class);
//        intent.putExtra("email", email);
//        intent.putExtra("password", password);
//            startActivity(intent);
            activity.finish();
    }


}
