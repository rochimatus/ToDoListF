package com.example.todolistf.modul.delete;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface DeleteContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile(String email, String password);
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
