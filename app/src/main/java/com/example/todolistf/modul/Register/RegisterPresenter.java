package com.example.todolistf.modul.Register;

import androidx.annotation.NonNull;

import com.example.todolistf.data.model.User;
import com.example.todolistf.data.source.session.SessionRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private final SessionRepository sessionRepository;
    private final FirebaseAuth mAuth;

    public RegisterPresenter(RegisterContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void performRegister(final String email, String password, String confirmPassword) {
        if(password.equals(confirmPassword)) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser user = mAuth.getCurrentUser();
                        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            @Override
                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                if (task.isSuccessful()) {
                                    String idToken = task.getResult().getToken();
                                    sessionRepository.setSessionData(new User(user.getEmail(), idToken));
                                } else {
                                    System.out.println(task.getException());
                                }
                            }
                        });
                        view.makeToast("Sukses Melakukan Registrasi");
                        view.showSuccess();
                    } else {
                        String errorMessage = task.getException().getMessage();
                        view.makeToast(errorMessage);
                        System.out.println(errorMessage);
                    }
                }
            });
        } else {
            view.makeToast("Password dan konfirmasi password tidak cocok");
        }
    }
}
