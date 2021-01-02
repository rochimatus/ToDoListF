package com.example.todolistf.modul.EditTask;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome(int status);
    }

    interface Presenter extends BasePresenter {
        void performUpdate(int id, String title, String description, String date);
    }
}
