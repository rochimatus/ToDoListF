package com.example.todolistf.modul.edit;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome(int status);
    }

    interface Presenter extends BasePresenter {
        void performUpdate(int id, String title, String description, String date);
    }
}
