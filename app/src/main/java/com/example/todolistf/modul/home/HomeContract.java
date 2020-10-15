package com.example.todolistf.modul.home;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showMessage(int status);
        void redirectToShow();
    }

    interface Presenter extends BasePresenter {
    }
}
