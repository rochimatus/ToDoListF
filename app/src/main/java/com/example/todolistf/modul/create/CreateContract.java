package com.example.todolistf.modul.create;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface CreateContract {
    interface View extends BaseView<CreateContract.Presenter> {
        void redirectToHome(int status);
    }

    interface Presenter extends BasePresenter {
        void performStore(String title, String description, String date);
    }
}
