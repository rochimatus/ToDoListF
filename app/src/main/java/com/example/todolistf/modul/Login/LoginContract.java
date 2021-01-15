package com.example.todolistf.modul.Login;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
        void firebaseAuthWithGoogle(String idToken);
        void isLoggedIn();
    }

    interface View extends BaseView<Presenter> {
        void redirectHome();
        void makeToast(String message);
    }
}
