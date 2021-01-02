package com.example.todolistf.modul.ShowTask;

import com.example.todolistf.base.BasePresenter;
import com.example.todolistf.base.BaseView;

import java.util.Date;

/**
 * Created by fahrul on 13/03/19.
 */

public interface ShowTaskContract {
    interface View extends BaseView<Presenter> {
        void showData(int id, String title, String description, Date date);
        void redirectToHome(int status);
        void redirectToEdit(int todoId);
    }

    interface Presenter extends BasePresenter {
        void performData(int todoId);
        void deleteData(int todoId);
        void doneToDo(int todoId);
    }
}
