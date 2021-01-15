package com.example.todolistf.modul.Register;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<RegisterContract> {
        void redirectHome();
        void showSuccess();
        void makeToast(String message);
    }

    interface Presenter extends BasePresenter {
        void performRegister(String email, String password, String confirmPassword);

    }
}
