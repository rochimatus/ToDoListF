package com.example.todolistf.modul.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.todolistf.R;
import com.example.todolistf.base.BaseFragment;
import com.example.todolistf.data.source.local.TaskTableHandler;
import com.example.todolistf.data.source.session.UserSessionRepository;
import com.example.todolistf.modul.Home.HomeActivity;
import com.example.todolistf.modul.Home.HomePresenter;
import com.example.todolistf.modul.Register.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.Utils;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {
    EditText etEmail;
    EditText etPassword;
    Button btLogin;
    ImageButton btGoogle;
    TextView tvRegister;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "GOOGLE AUTH";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()));
        mPresenter.start();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);

        etEmail = fragmentView.findViewById(R.id.etEmail);
        etPassword = fragmentView.findViewById(R.id.etPassword);
        tvRegister = fragmentView.findViewById(R.id.tvRegister);
        btLogin = fragmentView.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        btGoogle = fragmentView.findViewById(R.id.btGoogle);
        btGoogle.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        return fragmentView;
    }

    private void loginGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "onActivityResult: " + account.getId());
                mPresenter.firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "onActivityResult: " + e.getLocalizedMessage(), e);
            }
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btLogin.getId())
            onLogin();
        else if(v.getId() == btGoogle.getId())
            loginGoogle();
        else if(v.getId() == tvRegister.getId())
            redirectRegister();
    }

    private void redirectRegister() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
    }

    private void onLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mPresenter.performLogin(email, password);
    }

    @Override
    public void redirectHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.finishAffinity();
        startActivity(intent);
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
