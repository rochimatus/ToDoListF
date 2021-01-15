package com.example.todolistf.modul.Login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.todolistf.data.model.User;
import com.example.todolistf.data.source.session.SessionRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;
    private final FirebaseAuth mAuth;
    private final String TAG="LOGIN";

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {
        isLoggedIn();
    }

    @Override
    public void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    final FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    firebaseUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                sessionRepository.setSessionData(new User(firebaseUser.getEmail(), idToken));
                            } else {
                                System.out.println(task.getException());
                            }
                        }
                    });
                    view.makeToast("Berhasil Login");
                    view.redirectHome();
                } else {
                    view.makeToast(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            final FirebaseUser user = mAuth.getCurrentUser();
                            user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                @Override
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    if (task.isSuccessful()) {
                                        String idToken = task.getResult().getToken();
                                        sessionRepository.setSessionData(new User(user.getEmail(), idToken));
                                        User x = (User)sessionRepository.getSessionData();
                                        System.out.println(x.getEmail() + " " + x.getToken() );
                                        view.makeToast("Login Berhasil");
                                        view.redirectHome();
                                    } else {
                                        System.out.println(task.getException());
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            view.makeToast("Authentication failed");
                        }
                    }
                });
    }

    @Override
    public void isLoggedIn() {
        if(sessionRepository.getSessionData()!= null)
            view.redirectHome();
    }
}
